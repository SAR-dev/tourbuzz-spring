ALTER TABLE tour_logs
ADD CONSTRAINT unique_tour_date_in_tour_logs UNIQUE (tours_id, date);