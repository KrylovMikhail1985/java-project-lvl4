alter table url_check drop constraint if exists fk_url_check_url_id;
drop index if exists ix_url_check_url_id;

drop table if exists urls;

drop table if exists url_check;

