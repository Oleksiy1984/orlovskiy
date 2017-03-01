CREATE TABLE public.car
(
  id integer NOT NULL DEFAULT nextval('car_id_seq'::regclass),
  model character varying NOT NULL,
  make timestamp without time zone NOT NULL,
  id_engine bigint NOT NULL,
  price bigint NOT NULL,
  date date NOT NULL,
  CONSTRAINT car_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.car
  OWNER TO postgres;

CREATE TABLE public.car_ss
(
  id integer NOT NULL DEFAULT nextval('car_ss_id_seq'::regclass),
  id_ss bigint NOT NULL,
  id_car bigint NOT NULL,
  CONSTRAINT car_ss_pkey PRIMARY KEY (id),
  CONSTRAINT car_ss_id_car_fkey FOREIGN KEY (id_car)
      REFERENCES public.car (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT car_ss_id_ss_fkey FOREIGN KEY (id_ss)
      REFERENCES public.service_stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.car_ss
  OWNER TO postgres;

CREATE TABLE public.mechanic
(
  id integer NOT NULL DEFAULT nextval('mechanic_id_seq'::regclass),
  name character varying NOT NULL,
  surname character varying NOT NULL,
  ss_id bigint NOT NULL,
  CONSTRAINT mechanic_pkey PRIMARY KEY (id),
  CONSTRAINT mechanic_id_ss_fkey FOREIGN KEY (ss_id)
      REFERENCES public.service_stations (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.mechanic
  OWNER TO postgres;

CREATE TABLE public.service_stations
(
  id integer NOT NULL DEFAULT nextval('service_stations_id_seq'::regclass),
  address character varying NOT NULL,
  CONSTRAINT service_stations_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.service_stations
  OWNER TO postgres;