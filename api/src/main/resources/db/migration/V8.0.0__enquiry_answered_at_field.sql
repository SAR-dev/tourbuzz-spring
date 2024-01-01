ALTER TABLE public.enquiries ALTER COLUMN answer DROP NOT NULL;
ALTER TABLE public.enquiries ADD COLUMN answered_at timestamp;
