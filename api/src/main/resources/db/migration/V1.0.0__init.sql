--ENUMS

CREATE TYPE level_type AS ENUM('LEVEL1', 'LEVEL2', 'LEVEL3', 'LEVEL4', 'LEVEL5');
CREATE TYPE payment_type AS ENUM('BKASH', 'NAGAD');

--DOMAINS

CREATE DOMAIN _username AS TEXT
    CHECK (VALUE ~* '^(?=.{4,50}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$');
CREATE DOMAIN _email AS TEXT
    CHECK (VALUE ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
CREATE DOMAIN _link AS TEXT
    CHECK (VALUE ~* '^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w-]*)*\/?\??([^#\n\r]*)?#?([^\n\r]*)$');
CREATE DOMAIN number_list AS TEXT
    CHECK ( VALUE ~* '^\d+(,\d+)*|()$');
CREATE DOMAIN _slug AS TEXT
    CHECK (VALUE ~ '^[a-z0-9]+(?:-[a-z0-9]+)*$');


--FUNCTIONS

--Update Function
CREATE OR REPLACE FUNCTION "public".update_updated_at()
RETURNS trigger
LANGUAGE plpgsql
AS $function$
BEGIN
   NEW.updated_at = now();
   RETURN NEW;
END;
$function$
;

--Slugify Function
CREATE EXTENSION IF NOT EXISTS "unaccent";
CREATE OR REPLACE FUNCTION slugify("value" TEXT)
RETURNS TEXT AS $$
  WITH "unaccented" AS (
    SELECT unaccent("value") AS "value"
  ),
  "lowercase" AS (
    SELECT lower("value") AS "value"
    FROM "unaccented"
  ),
  "removed_quotes" AS (
    SELECT regexp_replace("value", '[''"]+', '', 'gi') AS "value"
    FROM "lowercase"
  ),
  "hyphenated" AS (
    SELECT regexp_replace("value", '[^a-z0-9\\-_]+', '-', 'gi') AS "value"
    FROM "removed_quotes"
  ),
  "trimmed" AS (
    SELECT regexp_replace(regexp_replace("value", '\-+$', ''), '^\-', '') AS "value"
    FROM "hyphenated"
  )
  SELECT "value" FROM "trimmed";
$$ LANGUAGE SQL STRICT IMMUTABLE;

--Title to Slug Function
CREATE FUNCTION public.set_slug_from_title() RETURNS trigger
    LANGUAGE plpgsql
    AS $$
BEGIN
  NEW.slug := slugify(NEW.title);
  RETURN NEW;
END
$$;

--number_list to array Function
CREATE OR REPLACE FUNCTION number_list_to_array(input_text TEXT)
RETURNS INTEGER[] AS $$
DECLARE
    num_array INTEGER[];
    num_list TEXT[];
    i INTEGER := 1;
BEGIN
    num_list := string_to_array(input_text, ',');
    WHILE i <= array_length(num_list, 1) LOOP
        num_array := num_array || ARRAY[CAST(num_list[i] AS INTEGER)];
        i := i + 1;
    END LOOP;
    RETURN num_array;
END;
$$ LANGUAGE plpgsql;

--Schema

CREATE TABLE images (
    id              serial PRIMARY KEY,
    title           text,
    source_url      text NOT NULL,
    thumbnail_url   text NOT NULL,
    optimized_url   text NOT NULL,
    deleted         boolean NOT NULL DEFAULT FALSE,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_images BEFORE UPDATE ON "public".images FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE divisions (
    id              serial PRIMARY KEY,
    name            text UNIQUE
);

CREATE TABLE districts (
    id              serial PRIMARY KEY,
    name            text UNIQUE,
    divisions_id    integer NOT NULL REFERENCES divisions(id)
);

CREATE TABLE users (
    id              serial PRIMARY KEY,
    email           _email NOT NULL UNIQUE,
    username        _username DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    images_id       integer REFERENCES images(id),
    name            text,
    last_logged_in  timestamp DEFAULT CURRENT_TIMESTAMP,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_users BEFORE UPDATE ON "public".users FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE writers (
    id              serial PRIMARY KEY,
    images_id       integer REFERENCES images(id),
    email           _email NOT NULL UNIQUE,
    username        _username DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    name            text NOT NULL UNIQUE,
    about           text,
    contact_no      text,
    website         _link,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_writers BEFORE UPDATE ON "public".writers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE articles (
    id              serial PRIMARY KEY,
    writers_id      integer NOT NULL REFERENCES writers(id),
    title           text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    summary         text NOT NULL,
    subtitle        text NOT NULL,
    md_desc         text NOT NULL,
    image_ids       number_list,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_articles BEFORE UPDATE ON "public".articles FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE enquiries (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES users(id),
    contact_no      text NOT NULL,
    question        text NOT NULL,
    answer          text,
    answered_at     timestamp,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_enquiries BEFORE UPDATE ON "public".enquiries FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_types (
    id              serial PRIMARY KEY,
    name            text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    details         text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tour_types BEFORE UPDATE ON "public".tour_types FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE organizers (
    id              serial PRIMARY KEY,
    name            text NOT NULL UNIQUE,
    username        _username NOT NULL UNIQUE,
    description     text NOT NULL,
    slogan          text NOT NULL,
    email           _email NOT NULL UNIQUE,
    images_id       integer REFERENCES images(id),
    verification    level_type,
    social_link     _link NOT NULL,
    contact_no      text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_organizers BEFORE UPDATE ON "public".organizers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tours (
    id              serial PRIMARY KEY,
    tour_types_id   integer NOT NULL REFERENCES tour_types(id),
    organizers_id   integer NOT NULL REFERENCES organizers(id),
    images_id       integer NOT NULL REFERENCES images(id),
    title           text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    md_desc         text NOT NULL,
    summary         text NOT NULL,
    included        text,
    additional_info text,
    group_size      text NOT NULL,
    transportation  text NOT NULL,
    instruction     text NOT NULL,
    cancellation    text NOT NULL,
    start_point     text NOT NULL,
    finish_point    text NOT NULL,
    image_ids       number_list,
    start_at        timestamp NOT NULL,
    finish_at       timestamp NOT NULL,
    last_sell_at    timestamp NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tours BEFORE UPDATE ON "public".tours FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE packages (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tours(id),
    title           text NOT NULL,
    description     text NOT NULL,
    price           integer NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_packages BEFORE UPDATE ON "public".packages FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE comments (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tours(id),
    users_id        integer NOT NULL REFERENCES users(id),
    title           text NOT NULL,
    description     text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_comments BEFORE UPDATE ON "public".comments FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE hotels (
    id              serial PRIMARY KEY,
    districts_id    integer NOT NULL REFERENCES districts(id),
    name            text NOT NULL UNIQUE,
    map_link        _link NOT NULL,
    website         _link,
    review          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_hotels BEFORE UPDATE ON "public".hotels FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE places (
    id              serial PRIMARY KEY,
    images_id       integer NOT NULL REFERENCES images(id),
    districts_id    integer NOT NULL REFERENCES districts(id),
    name            text NOT NULL UNIQUE,
    postal_code     text,
    map_link        _link NOT NULL,
    description     text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_places BEFORE UPDATE ON "public".places FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_logs (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tours(id),
    hotels_id       integer NOT NULL REFERENCES hotels(id),
    title           text NOT NULL,
    description     text NOT NULL,
    date            date NOT NULL,
    image_ids       number_list,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tour_logs BEFORE UPDATE ON "public".tour_logs FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_logs_places (
    id              serial PRIMARY KEY,
    places_id       integer NOT NULL REFERENCES places(id),
    tour_logs_id    integer NOT NULL REFERENCES tour_logs(id),
    CONSTRAINT unq_tour_logs_places UNIQUE ( tour_logs_id, places_id )
);

CREATE TABLE tickets (
    id              serial PRIMARY KEY,
    packages_id     integer NOT NULL REFERENCES tours(id),
    users_id        integer NOT NULL REFERENCES users(id),
    paid_amount     integer NOT NULL,
    payment_by      payment_type,
    payment_id      text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unq_tickets_payments UNIQUE ( payment_by, payment_id )
);
CREATE TRIGGER update_updated_at_tickets BEFORE UPDATE ON "public".tickets FOR EACH ROW EXECUTE FUNCTION update_updated_at();

