ALTER TABLE public.writers ADD deleted boolean NOT NULL DEFAULT false;
ALTER TABLE public.articles ADD deleted boolean NOT NULL DEFAULT false;