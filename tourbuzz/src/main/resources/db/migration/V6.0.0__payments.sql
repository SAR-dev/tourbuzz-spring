CREATE TABLE public.payments (
    id              serial PRIMARY KEY,
    users_id        integer REFERENCES auth.users(id),
    invoice_no      text NOT NULL UNIQUE,
    reference_no    text,
    payment_type    text,
    transaction_id  text,
    payment_id      text,
    amount          numeric,
    currency        text,
    completed       boolean DEFAULT FALSE,
    paid_at         timestamp,
    created_at      timestamp DEFAULT CURRENT_TIMESTAMP,
    updated_at      timestamp DEFAULT CURRENT_TIMESTAMP
);
CREATE TRIGGER update_updated_at_payments BEFORE UPDATE ON public.payments FOR EACH ROW EXECUTE FUNCTION update_updated_at();

-- Create a sequence to generate unique invoice numbers
CREATE SEQUENCE invoice_number_seq START WITH 10000000;

-- Create a trigger function to generate and set invoice number
CREATE OR REPLACE FUNCTION generate_invoice_number()
RETURNS TRIGGER AS $$
BEGIN
    -- Set the invoice number to the next value from the sequence
    NEW.invoice_no = LPAD(nextval('invoice_number_seq')::text, 8, '0');
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Create a trigger to call the function before inserting into the payments table
CREATE TRIGGER set_invoice_number
BEFORE INSERT ON public.payments
FOR EACH ROW
EXECUTE FUNCTION generate_invoice_number();
