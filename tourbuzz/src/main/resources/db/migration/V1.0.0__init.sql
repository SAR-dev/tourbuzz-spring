DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = 'auth') THEN
    CREATE SCHEMA auth;
  END IF;
END $$;

DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = 'tour') THEN
    CREATE SCHEMA tour;
  END IF;
END $$;

DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_namespace WHERE nspname = 'public') THEN
    CREATE SCHEMA public;
  END IF;
END $$;

--ENUMS

--public
CREATE TYPE public.level_type AS ENUM('LEVEL1', 'LEVEL2', 'LEVEL3', 'LEVEL4', 'LEVEL5');
CREATE TYPE public.payment_type AS ENUM('BKASH', 'NAGAD');
CREATE TYPE public.status_type AS ENUM('DRAFT', 'PUBLISHED');

--auth
CREATE TYPE auth.level_type AS ENUM('LEVEL1', 'LEVEL2', 'LEVEL3', 'LEVEL4', 'LEVEL5');
CREATE TYPE auth.payment_type AS ENUM('BKASH', 'NAGAD');
CREATE TYPE auth.status_type AS ENUM('DRAFT', 'PUBLISHED');

--tour
CREATE TYPE tour.level_type AS ENUM('LEVEL1', 'LEVEL2', 'LEVEL3', 'LEVEL4', 'LEVEL5');
CREATE TYPE tour.payment_type AS ENUM('BKASH', 'NAGAD');
CREATE TYPE tour.status_type AS ENUM('DRAFT', 'PUBLISHED');

--DOMAINS

--public
CREATE DOMAIN public._username AS TEXT
    CHECK (VALUE ~* '^(?=.{4,50}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$');
CREATE DOMAIN public._email AS TEXT
    CHECK (VALUE ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
CREATE DOMAIN public._link AS TEXT
    CHECK (VALUE ~* '^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w-]*)*\/?\??([^#\n\r]*)?#?([^\n\r]*)$');
CREATE DOMAIN public.number_list AS TEXT
    CHECK ( VALUE ~* '^\d+(,\d+)*|()$');
CREATE DOMAIN public._slug AS TEXT
    CHECK (VALUE ~ '^[a-z0-9]+(?:-[a-z0-9]+)*$');

--auth
CREATE DOMAIN auth._username AS TEXT
    CHECK (VALUE ~* '^(?=.{4,50}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$');
CREATE DOMAIN auth._email AS TEXT
    CHECK (VALUE ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
CREATE DOMAIN auth._link AS TEXT
    CHECK (VALUE ~* '^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w-]*)*\/?\??([^#\n\r]*)?#?([^\n\r]*)$');
CREATE DOMAIN auth.number_list AS TEXT
    CHECK ( VALUE ~* '^\d+(,\d+)*|()$');
CREATE DOMAIN auth._slug AS TEXT
    CHECK (VALUE ~ '^[a-z0-9]+(?:-[a-z0-9]+)*$');

--tour
CREATE DOMAIN tour._username AS TEXT
    CHECK (VALUE ~* '^(?=.{4,50}$)(?![_.-])(?!.*[_.-]{2})[a-zA-Z0-9._-]+(?<![_.-])$');
CREATE DOMAIN tour._email AS TEXT
    CHECK (VALUE ~* '^[A-Za-z0-9._+%-]+@[A-Za-z0-9.-]+[.][A-Za-z]+$');
CREATE DOMAIN tour._link AS TEXT
    CHECK (VALUE ~* '^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w-]*)*\/?\??([^#\n\r]*)?#?([^\n\r]*)$');
CREATE DOMAIN tour.number_list AS TEXT
    CHECK ( VALUE ~* '^\d+(,\d+)*|()$');
CREATE DOMAIN tour._slug AS TEXT
    CHECK (VALUE ~ '^[a-z0-9]+(?:-[a-z0-9]+)*$');


--Update Function

--public
CREATE OR REPLACE FUNCTION public.update_updated_at()
RETURNS trigger
LANGUAGE plpgsql
AS $function$
BEGIN
   NEW.updated_at = now();
   RETURN NEW;
END;
$function$
;

--auth
CREATE OR REPLACE FUNCTION auth.update_updated_at()
RETURNS trigger
LANGUAGE plpgsql
AS $function$
BEGIN
   NEW.updated_at = now();
   RETURN NEW;
END;
$function$
;

--tour
CREATE OR REPLACE FUNCTION tour.update_updated_at()
RETURNS trigger
LANGUAGE plpgsql
AS $function$
BEGIN
   NEW.updated_at = now();
   RETURN NEW;
END;
$function$
;

--number_list to array Function

--public
CREATE OR REPLACE FUNCTION public.number_list_to_array(input_text TEXT)
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

--auth
CREATE OR REPLACE FUNCTION auth.number_list_to_array(input_text TEXT)
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

--tour
CREATE OR REPLACE FUNCTION tour.number_list_to_array(input_text TEXT)
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

CREATE TABLE public.images (
    id              serial PRIMARY KEY,
    title           text,
    source_url      text NOT NULL,
    thumbnail_url   text NOT NULL,
    optimized_url   text NOT NULL,
    deleted         boolean NOT NULL DEFAULT FALSE,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_images BEFORE UPDATE ON public.images FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.divisions (
    id              serial PRIMARY KEY,
    name            text UNIQUE
);

CREATE TABLE public.districts (
    id              serial PRIMARY KEY,
    name            text UNIQUE,
    divisions_id    integer NOT NULL REFERENCES public.divisions(id)
);

CREATE TABLE auth.users (
    id              serial PRIMARY KEY,
    email           _email NOT NULL UNIQUE,
    username        _username DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    images_id       integer REFERENCES public.images(id),
    name            text,
    last_logged_in  timestamp DEFAULT CURRENT_TIMESTAMP,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_users BEFORE UPDATE ON auth.users FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.writers (
    id              serial PRIMARY KEY,
    images_id       integer REFERENCES public.images(id),
    email           _email NOT NULL UNIQUE,
    username        _username DEFAULT gen_random_uuid() NOT NULL UNIQUE,
    name            text NOT NULL UNIQUE,
    about           text,
    contact_no      text,
    website         _link,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_writers BEFORE UPDATE ON public.writers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.articles (
    id              serial PRIMARY KEY,
    writers_id      integer NOT NULL REFERENCES public.writers(id),
    title           text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    category        text NOT NULL,
    summary         text NOT NULL,
    subtitle        text NOT NULL,
    md_desc         text NOT NULL,
    image_ids       number_list,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_articles BEFORE UPDATE ON public.articles FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.enquiries (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES auth.users(id),
    contact_no      text NOT NULL,
    question        text NOT NULL,
    answer          text,
    answered_at     timestamp,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_enquiries BEFORE UPDATE ON public.enquiries FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.tour_types (
    id              serial PRIMARY KEY,
    name            text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    details         text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tour_types BEFORE UPDATE ON tour.tour_types FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.organizers (
    id              serial PRIMARY KEY,
    name            text NOT NULL UNIQUE,
    username        _username NOT NULL UNIQUE,
    description     text NOT NULL,
    slogan          text NOT NULL,
    email           _email NOT NULL UNIQUE,
    images_id       integer REFERENCES public.images(id),
    verification    level_type,
    social_link     _link NOT NULL,
    contact_no      text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_organizers BEFORE UPDATE ON tour.organizers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.tours (
    id              serial PRIMARY KEY,
    tour_types_id   integer NOT NULL REFERENCES tour.tour_types(id),
    organizers_id   integer NOT NULL REFERENCES tour.organizers(id),
    images_id       integer NOT NULL REFERENCES public.images(id),
    video_link      _link,
    title           text NOT NULL UNIQUE,
    slug            _slug NOT NULL UNIQUE,
    highlights      text NOT NULL,
    md_desc         text NOT NULL,
    cancel_ins      text NOT NULL,
    start_ins       text NOT NULL,
    finish_ins      text NOT NULL,
    faq             text NOT NULL,
    group_size      text NOT NULL,
    group_info      text NOT NULL,
    transports      text NOT NULL,
    image_ids       number_list,
    start_at        timestamp NOT NULL,
    finish_at       timestamp NOT NULL,
    last_sell_at    timestamp NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_tours BEFORE UPDATE ON tour.tours FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.packages (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tour.tours(id),
    title           text NOT NULL,
    included        text NOT NULL,
    excluded        text NOT NULL,
    conditions      text NOT NULL,
    price           integer NOT NULL,
    status          status_type NOT NULL DEFAULT 'PUBLISHED',
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_title_in_packages UNIQUE (tours_id, title)
);
CREATE TRIGGER update_updated_at_packages BEFORE UPDATE ON tour.packages FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.tour_comments (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tour.tours(id),
    users_id        integer NOT NULL REFERENCES auth.users(id),
    title           text NOT NULL,
    description     text NOT NULL,
    rating          integer CHECK (rating > 0 AND rating <= 5),
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_comments BEFORE UPDATE ON tour.tour_comments FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.hotels (
    id              serial PRIMARY KEY,
    districts_id    integer NOT NULL REFERENCES public.districts(id),
    images_id       integer NOT NULL REFERENCES public.images(id),
    name            text NOT NULL UNIQUE,
    map_link        _link NOT NULL,
    website         _link,
    review          text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_hotels BEFORE UPDATE ON public.hotels FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.places (
    id              serial PRIMARY KEY,
    images_id       integer NOT NULL REFERENCES public.images(id),
    districts_id    integer NOT NULL REFERENCES public.districts(id),
    name            text NOT NULL UNIQUE,
    type            text NOT NULL,
    postal_code     text,
    map_link        _link NOT NULL,
    description     text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_places BEFORE UPDATE ON public.places FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.tour_logs (
    id              serial PRIMARY KEY,
    tours_id        integer NOT NULL REFERENCES tour.tours(id),
    hotels_id       integer REFERENCES public.hotels(id),
    title           text NOT NULL,
    description     text NOT NULL,
    date            date NOT NULL,
    image_ids       number_list,
    status          status_type NOT NULL DEFAULT 'PUBLISHED',
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unique_tour_date_in_tour_logs UNIQUE ( tours_id, date )
);
CREATE TRIGGER update_updated_at_tour_logs BEFORE UPDATE ON tour.tour_logs FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE tour.tour_logs_places (
    id              serial PRIMARY KEY,
    places_id       integer NOT NULL REFERENCES public.places(id),
    tour_logs_id    integer NOT NULL REFERENCES tour.tour_logs(id),
    CONSTRAINT unq_tour_logs_places UNIQUE ( tour_logs_id, places_id )
);

CREATE TABLE tour.tickets (
    id              serial PRIMARY KEY,
    packages_id     integer NOT NULL REFERENCES tour.tours(id),
    users_id        integer NOT NULL REFERENCES auth.users(id),
    paid_amount     integer NOT NULL,
    payment_by      payment_type,
    payment_id      text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unq_tickets_payments UNIQUE ( payment_by, payment_id )
);
CREATE TRIGGER update_updated_at_tickets BEFORE UPDATE ON tour.tickets FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.ads (
    id              serial PRIMARY KEY,
    title           text NOT NULL,
    subtitle        text,
    link            text NOT NULL,
    image_url       text NOT NULL,
    active          boolean NOT NULL DEFAULT FALSE,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_ads BEFORE UPDATE ON public.ads FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE auth.admin(
    Lock char(1) not null DEFAULT 'X',
    secret text not null,
    constraint PK_T1 PRIMARY KEY (Lock),
    constraint CK_T1_Locked CHECK (Lock='X')
);
INSERT INTO auth.admin (secret) VALUES (gen_random_uuid());