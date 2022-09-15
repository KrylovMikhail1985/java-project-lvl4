-- apply changes
create table site_check (
  id                            bigint generated by default as identity not null,
  url_id                        bigint not null,
  title                         varchar(255),
  h1                            varchar(255),
  description                   varchar(255),
  checking_date                 timestamp not null,
  constraint pk_site_check primary key (id)
);

