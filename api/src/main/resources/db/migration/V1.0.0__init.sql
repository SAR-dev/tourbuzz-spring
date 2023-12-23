--ENUMS

CREATE TYPE provider_type AS ENUM('OKTA', 'AUTH0', 'LINKEDIN', 'AMAZON', 'APPLE', 'MICROSOFT', 'GITHUB', 'TWITTER', 'FACEBOOK', 'GOOGLE');
CREATE TYPE level_type AS ENUM('LEVEL1', 'LEVEL2', 'LEVEL3', 'LEVEL4', 'LEVEL5');

--DOMAINS

CREATE DOMAIN _username AS TEXT
    CHECK (VALUE ~* '^(?=.{4,50}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$');
CREATE DOMAIN _email AS TEXT
    CHECK (VALUE ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
CREATE DOMAIN _link AS TEXT
    CHECK (VALUE ~* '^[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)$');

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

--Unique Array Integer Function
CREATE FUNCTION public.array_unique_int (a integer[]) RETURNS integer[] as $$
  SELECT array (
    SELECT DISTINCT v FROM unnest(a) AS b(v)
  )
$$ LANGUAGE SQL STRICT;

--Schema

CREATE TABLE users (
    id              serial PRIMARY KEY,
    email           _email NOT NULL UNIQUE,
    username        _username DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    avatar          text,
    name            text,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_users BEFORE UPDATE ON "public".users FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE images (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES users(id),
    title           text,
    path            text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_images BEFORE UPDATE ON "public".images FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE articles (
    id              serial PRIMARY KEY,
    slug            text NOT NULL UNIQUE,
    title           text NOT NULL,
    summary         text NOT NULL,
    subtitle        text NOT NULL,
    details         text NOT NULL,
    writer          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    images          text[],
    tags            text[]
);
CREATE TRIGGER update_updated_at_articles BEFORE UPDATE ON "public".articles FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE enquiries (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES users(id),
    contact_number  text NOT NULL,
    message         text NOT NULL,
    email           text NOT NULL,
    answer          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_enquiries BEFORE UPDATE ON "public".enquiries FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_types (
    id              serial PRIMARY KEY,
    details         text NOT NULL,
    name            text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tour_types BEFORE UPDATE ON "public".tour_types FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE organizers (
    id              serial PRIMARY KEY,
    username        _username NOT NULL UNIQUE,
    email           _email NOT NULL UNIQUE,
    verification    level_type,
    name            text NOT NULL,
    social_link     text NOT NULL,
    contact_number  text NOT NULL,
    avatar          text NOT NULL,
    description     text NOT NULL,
    slogan          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_organizers BEFORE UPDATE ON "public".organizers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tours (
    id              serial PRIMARY KEY,
    tour_types_id   integer NOT NULL REFERENCES tour_types(id),
    organizers_id   integer NOT NULL REFERENCES organizers(id),
    price           integer NOT NULL,
    slug            text NOT NULL UNIQUE,
    thumbnail       text NOT NULL,
    title           text NOT NULL,
    organizer       text NOT NULL,
    description     text NOT NULL,
    start_at        timestamp NOT NULL,
    finish_at       timestamp NOT NULL,
    start_point     text NOT NULL,
    end_point       text NOT NULL,
    included        text,
    additional_info text,
    last_sell_at    timestamp NOT NULL,
    summary         text NOT NULL,
    verification    text NOT NULL,
    group_size      text NOT NULL,
    transportation  text NOT NULL,
    instruction     text NOT NULL,
    cancellation    text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    images text[]
);
CREATE TRIGGER update_updated_at_tours BEFORE UPDATE ON "public".tours FOR EACH ROW EXECUTE FUNCTION update_updated_at();

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
    name            text NOT NULL UNIQUE,
    map_link        _link NOT NULL,
    review          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_hotels BEFORE UPDATE ON "public".hotels FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE places (
    id              serial PRIMARY KEY,
    name            text NOT NULL UNIQUE,
    division        text NOT NULL,
    map_link        _link NOT NULL,
    thumbnail       text NOT NULL,
    description     text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    images text[]
);
CREATE TRIGGER update_updated_at_places BEFORE UPDATE ON "public".places FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_logs (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tours(id),
    hotels_id       integer NOT NULL REFERENCES hotels(id),
    title           text NOT NULL,
    description     text NOT NULL,
    thumbnail       text NOT NULL,
    date            date NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tour_logs BEFORE UPDATE ON "public".tour_logs FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour_logs_places (
    id              serial PRIMARY KEY,
    places_id       integer NOT NULL REFERENCES places(id),
    tour_logs_id    integer NOT NULL REFERENCES tour_logs(id)
);

CREATE TABLE tickets (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tours(id),
    users_id        integer NOT NULL REFERENCES users(id),
    paid_amount     integer NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tickets BEFORE UPDATE ON "public".tickets FOR EACH ROW EXECUTE FUNCTION update_updated_at();

