--24/11/2013
------------
DROP TABLE inscrito;
DROP TABLE curso;
DROP TABLE eventopalestra;
DROP TABLE minicurso;
DROP TABLE workshop;
DROP TABLE instrutor;
DROP TABLE orador;
CREATE TABLE eventoatividade
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_participante bigint NOT NULL,
  id_lugarespaco bigint NOT NULL,
  tipoatividade character varying(20) NOT NULL,
  titulo character varying(100) NOT NULL,
  descricao character varying(4096),
  conteudo character varying(4096),
  horainicio character varying(5) NOT NULL,
  horafim character varying(5) NOT NULL,
  valoratividade numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventoatividade PRIMARY KEY (id),
  CONSTRAINT fk_eventoatividade_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventoatividade_lugarespaco FOREIGN KEY (id_lugarespaco)
      REFERENCES lugarespaco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventoatividade_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);
CREATE TABLE eventoinscrito
(
  id bigint NOT NULL,
  id_participante bigint NOT NULL,
  id_eventoatividade bigint,
  valorinscrito numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventoinscrito PRIMARY KEY (id),
  CONSTRAINT fk_eventoinscrito_eventoatividade FOREIGN KEY (id_eventoatividade)
      REFERENCES eventoatividade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
DROP TABLE eventoorganizador;
CREATE TABLE eventotipo
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  id_participante bigint NOT NULL,
  tipoparticipante character varying(20) NOT NULL,
  CONSTRAINT pk_eventotipo PRIMARY KEY (id),
  CONSTRAINT fk_eventotipo FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventotipo_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT uk_eventotipo_evento_participante UNIQUE (id_evento, id_participante, tipoparticipante)
);
DROP TABLE eventoinscrito;
DROP TABLE eventoatividade;
CREATE TABLE eventoatividade
(
  id bigint NOT NULL,
  id_eventotipo bigint NOT NULL,
  id_lugarespaco bigint NOT NULL,
  tipoatividade character varying(20) NOT NULL,
  titulo character varying(100) NOT NULL,
  descricao character varying(4096),
  conteudo character varying(4096),
  horainicio character varying(5),
  horafim character varying(5),
  valoratividade numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventoatividade PRIMARY KEY (id),
  CONSTRAINT fk_eventoatividade_eventotipo FOREIGN KEY (id_eventotipo)
      REFERENCES eventotipo (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventoatividade_lugarespaco FOREIGN KEY (id_lugarespaco)
      REFERENCES lugarespaco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
CREATE TABLE eventoinscrito
(
  id bigint NOT NULL,
  id_participante bigint NOT NULL,
  id_eventoatividade bigint,
  valorinscrito numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventoinscrito PRIMARY KEY (id),
  CONSTRAINT fk_eventoinscrito_eventoatividade FOREIGN KEY (id_eventoatividade)
      REFERENCES eventoatividade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
DROP TABLE apoiador;
DROP TABLE patrocinador;
DROP TABLE realizador;
DROP TABLE patrocinio;
CREATE TABLE eventopatrocinio
(
  id bigint NOT NULL,
  id_eventotipo bigint NOT NULL,
  descricao character varying(255) NOT NULL,
  valorpatrocinio numeric(9,2) DEFAULT 0 NOT NULL,
  CONSTRAINT pk_eventopatrocinio PRIMARY KEY (id),
  CONSTRAINT fk_eventopatrocinio_eventotipo FOREIGN KEY (id_eventotipo)
      REFERENCES eventotipo (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
ALTER TABLE eventoatividade
  DROP CONSTRAINT fk_eventoatividade_lugarespaco;
ALTER TABLE eventocurso
  DROP CONSTRAINT fk_eventocurso_lugarespaco;
DROP TABLE lugarespaco;
DROP TABLE eventolocal;
DROP TABLE lugar;
CREATE TABLE participanteespaco
(
  id bigint NOT NULL,
  id_participante bigint NOT NULL,
  descricao character varying(100) NOT NULL,
  capacidade integer,
  complemento character varying(255),
  CONSTRAINT pk_participanteespaco PRIMARY KEY (id),
  CONSTRAINT fk_participanteespaco_participante FOREIGN KEY (id_participante)
      REFERENCES participante (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE
);
delete from eventoatividade;
ALTER TABLE eventoatividade RENAME id_lugarespaco  TO id_participanteespaco;
ALTER TABLE eventoatividade
  ADD CONSTRAINT fk_eventoatividade_participanteespaco FOREIGN KEY (id_participanteespaco)
      REFERENCES participanteespaco (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT;
CREATE TABLE eventolocal (
    id bigint NOT NULL,
    id_eventotipo bigint NOT NULL,
    valorlocacao numeric(9,2) DEFAULT 0.00 NOT NULL,
  CONSTRAINT pk_eventolocal PRIMARY KEY (id),
  CONSTRAINT fk_eventolocal_eventotipo FOREIGN KEY (id_eventotipo)
      REFERENCES eventotipo (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
CREATE TABLE public.eventofinanceiro
(
   id bigint NOT NULL, 
   id_evento bigint NOT NULL, 
   id_eventotipo bigint, 
   descricao character varying(255), 
   valorfinanceiro numeric(9,2) NOT NULL DEFAULT 0, 
   tipofinanceiro character varying(20) NOT NULL DEFAULT 'PREVISTO',
   dataprevisto timestamp with time zone,
   datarealizado timestamp with time zone,
  CONSTRAINT pk_eventofinanceiro PRIMARY KEY (id),
  CONSTRAINT fk_eventofinanceiro_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_eventofinanceiro_eventotipo FOREIGN KEY (id_eventotipo)
      REFERENCES eventotipo (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
ALTER TABLE eventotipo
  DROP CONSTRAINT uk_eventotipo_evento_participante;
DROP TABLE eventocurso;
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.2-Beta');