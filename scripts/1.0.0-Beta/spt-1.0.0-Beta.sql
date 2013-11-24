--18/11/2013
------------
ALTER TABLE palestra
  ADD COLUMN titulo character varying(100) NOT NULL;
ALTER TABLE palestra
  ADD COLUMN descricao character varying(4096);
ALTER TABLE palestra
  ADD COLUMN horainicio character varying(5) NOT NULL;
ALTER TABLE palestra
  ADD COLUMN horafim character varying(5) NOT NULL;
ALTER TABLE lugarespaco
   ALTER COLUMN descricao TYPE character varying(100);
ALTER TABLE palestrante
  DROP CONSTRAINT uk_palestrante_evento_participante;
ALTER TABLE palestrante
  DROP CONSTRAINT fk_palestrante_participante;
ALTER TABLE palestrante
  ADD CONSTRAINT fk_palestrante_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE;
DROP TABLE local;
CREATE TABLE eventolocal
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_lugar bigint NOT NULL,
  valorlocacao numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventolocal PRIMARY KEY (id),
  CONSTRAINT fk_eventolocal_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_eventolocal_lugar FOREIGN KEY (id_lugar)
      REFERENCES lugar (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT uk_eventolocal_evento_lugar UNIQUE (id_evento, id_lugar)
);
DROP TABLE organizador;
CREATE TABLE eventoorganizador
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_participante bigint NOT NULL,
  CONSTRAINT pk_eventoorganizador PRIMARY KEY (id),
  CONSTRAINT fk_eventoorganizador_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventoorganizador_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT uk_eventoorganizador_evento_participante UNIQUE (id_evento, id_participante)
);
DROP TABLE inscrito;
DROP TABLE palestra;
DROP TABLE palestrante;
CREATE TABLE eventopalestra
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_participante bigint NOT NULL,
  id_lugarespaco bigint NOT NULL,
  titulo character varying(100) NOT NULL,
  descricao character varying(4096),
  horainicio character varying(5) NOT NULL,
  horafim character varying(5) NOT NULL,
  valorpalestrante numeric(9,2) NOT NULL DEFAULT 0.00,  
  CONSTRAINT pk_eventopalestra PRIMARY KEY (id),
  CONSTRAINT fk_eventopalestra_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventopalestra_lugarespaco FOREIGN KEY (id_lugarespaco)
      REFERENCES lugarespaco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventopalestra_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE inscrito
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_participante bigint NOT NULL,
  valorinscrito numeric(9,2) NOT NULL DEFAULT 0.00,
  id_workshop bigint,
  id_palestra bigint,
  id_curso bigint,
  id_minicurso bigint,
  CONSTRAINT pk_inscrito PRIMARY KEY (id),
  CONSTRAINT fk_inscrito_curso FOREIGN KEY (id_curso)
      REFERENCES curso (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_inscrito_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_inscrito_minicurso FOREIGN KEY (id_minicurso)
      REFERENCES minicurso (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_inscrito_palestra FOREIGN KEY (id_palestra)
      REFERENCES eventopalestra (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_inscrito_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_inscrito_workshop FOREIGN KEY (id_workshop)
      REFERENCES workshop (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT uk_inscrito_evento_participante UNIQUE (id_evento, id_participante)
);
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.1-Beta');
