CREATE TYPE public.manager_position_type AS ENUM('ADMIN', 'STAFF');
CREATE TYPE tour.manager_position_type AS ENUM('ADMIN', 'STAFF');

CREATE TABLE tour.organizer_managers (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES auth.users(id),
    organizers_id   integer NOT NULL REFERENCES tour.organizers(id),
    position        manager_position_type NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unq_organizer_managers UNIQUE ( users_id, organizers_id )
);
CREATE TRIGGER update_updated_at_organizer_managers BEFORE UPDATE ON tour.organizer_managers FOR EACH ROW EXECUTE FUNCTION update_updated_at();

CREATE TABLE public.hotel_managers (
    id              serial PRIMARY KEY,
    users_id        integer NOT NULL REFERENCES auth.users(id),
    hotels_id       integer NOT NULL REFERENCES public.hotels(id),
    position        manager_position_type NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unq_hotel_managers UNIQUE ( users_id, hotels_id )
);
CREATE TRIGGER update_updated_at_hotel_managers BEFORE UPDATE ON public.hotel_managers FOR EACH ROW EXECUTE FUNCTION update_updated_at();
