--ENUMS

--public
CREATE TYPE public.emoji_reference_type AS ENUM('TOUR', 'ARTICLE', 'TOUR_COMMENT');

CREATE TABLE public.emoji_reactions (
    id              serial PRIMARY KEY,
    reference_type  emoji_reference_type NOT NULL,
    reference_id    integer NOT NULL,
    users_id        integer NOT NULL REFERENCES auth.users(id),
    emoji_hex       text NOT NULL,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT unq_emoji_reactions UNIQUE ( reference_type, reference_id, users_id, emoji_hex )
);
CREATE TRIGGER update_updated_at_emoji_reactions BEFORE UPDATE ON public.emoji_reactions FOR EACH ROW EXECUTE FUNCTION update_updated_at();