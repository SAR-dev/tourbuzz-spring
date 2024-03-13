CREATE TABLE public.payments (
    id              serial PRIMARY KEY,
    users_id        integer REFERENCES auth.users(id),
    reference_id    text DEFAULT UPPER(REPLACE(gen_random_uuid()::text, '-', '')) NOT NULL UNIQUE,
    payment_type    text,
    transaction_id  text,
    payment_id      text,
    invoice_no      text,
    amount          numeric,
    currency        text,
    completed       boolean DEFAULT FALSE,
    paid_at         timestamp,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_payments BEFORE UPDATE ON public.payments FOR EACH ROW EXECUTE FUNCTION update_updated_at();
