--03/12/2013
------------
ALTER TABLE eventofinanceiro
   ALTER COLUMN dataprevisto TYPE timestamp without time zone;
ALTER TABLE eventofinanceiro
   ALTER COLUMN datarealizado TYPE timestamp without time zone;
CREATE TABLE eventoinscricao
(
  id bigint NOT NULL,
  id_evento bigint NOT NULL,
  descricao character varying(1024) NOT NULL,
  valorinscricao numeric(9,2) NOT NULL DEFAULT 0.00,
  CONSTRAINT pk_eventoinscricao PRIMARY KEY (id),
  CONSTRAINT fk_eventoinscricao_evento FOREIGN KEY (id_evento)
      REFERENCES evento (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
CREATE TABLE eventoinscricaoatividade
(
  id_eventoinscricao bigint NOT NULL,
  id_eventoatividade bigint NOT NULL,
  CONSTRAINT pk_eventoinscricaoatividade PRIMARY KEY (id_eventoinscricao, id_eventoatividade),
  CONSTRAINT fk_eventoinscricaoatividade_eventoinscricao FOREIGN KEY (id_eventoinscricao)
      REFERENCES eventoinscricao (id) MATCH SIMPLE
      ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT fk_eventoinscricaoatividade_eventoatividade FOREIGN KEY (id_eventoatividade)
      REFERENCES eventoatividade (id) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--Registra versão
insert into versao values ( nextval('identificador'), '1.0.3-Beta');