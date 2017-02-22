CREATE TABLE public.engine
(
  id integer NOT NULL DEFAULT nextval('engine_id_seq'::regclass),
  displacement double precision NOT NULL,
  power bigint NOT NULL,
  CONSTRAINT engine_pkey PRIMARY KEY (id)
)

CREATE TABLE public.car
(
  id integer NOT NULL DEFAULT nextval('car_id_seq'::regclass),
  model character varying NOT NULL,
  make date NOT NULL,
  id_engine bigint NOT NULL,
  price bigint NOT NULL,
  CONSTRAINT car_pkey PRIMARY KEY (id),
  CONSTRAINT car_id_engine_fkey FOREIGN KEY (id_engine)
      REFERENCES public.engine (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)