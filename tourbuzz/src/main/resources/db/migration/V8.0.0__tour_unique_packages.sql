ALTER TABLE packages
ADD CONSTRAINT unique_title_in_packages UNIQUE (tours_id, title);