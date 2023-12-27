ALTER DOMAIN number_list DROP CONSTRAINT number_list_check;
ALTER DOMAIN number_list
ADD CONSTRAINT number_list_check CHECK (VALUE ~* '^\d+(,\d+)*|()$');