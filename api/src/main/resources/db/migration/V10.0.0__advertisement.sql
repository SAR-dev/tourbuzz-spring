CREATE TABLE ads (
    id              serial PRIMARY KEY,
    title           text NOT NULL,
    subtitle        text,
    link            text NOT NULL,
    image_url       text NOT NULL,
    active          boolean NOT NULL DEFAULT FALSE,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_ads BEFORE UPDATE ON "public".ads FOR EACH ROW EXECUTE FUNCTION update_updated_at();
