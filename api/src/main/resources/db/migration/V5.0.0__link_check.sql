ALTER DOMAIN _link DROP CONSTRAINT _link_check;
ALTER DOMAIN _link
ADD CONSTRAINT _link_check CHECK (VALUE ~* '^(https?:\/\/)?([\w\-])+\.{1}([a-zA-Z]{2,63})([\/\w-]*)*\/?\??([^#\n\r]*)?#?([^\n\r]*)$');