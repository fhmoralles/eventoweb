--
-- PostgreSQL database dump
--

-- Dumped from database version 9.2.1
-- Dumped by pg_dump version 9.2.1
-- Started on 2013-11-16 14:20:44

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 190 (class 3079 OID 11727)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2124 (class 0 OID 0)
-- Dependencies: 190
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 181 (class 1259 OID 47232)
-- Name: apoiador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE apoiador (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL
);


ALTER TABLE public.apoiador OWNER TO postgres;

--
-- TOC entry 186 (class 1259 OID 47325)
-- Name: curso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE curso (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_instrutor bigint NOT NULL,
    id_lugarespaco bigint NOT NULL
);


ALTER TABLE public.curso OWNER TO postgres;

--
-- TOC entry 172 (class 1259 OID 46918)
-- Name: evento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE evento (
    id bigint NOT NULL,
    nome character varying(255) NOT NULL,
    descricao character varying(4096),
    dataevento date NOT NULL,
    datacadastro timestamp with time zone NOT NULL,
    id_participante bigint NOT NULL
);


ALTER TABLE public.evento OWNER TO postgres;

--
-- TOC entry 168 (class 1259 OID 46732)
-- Name: identificador; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE identificador
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.identificador OWNER TO postgres;

--
-- TOC entry 184 (class 1259 OID 47283)
-- Name: inscrito; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE inscrito (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL,
    valorinscrito numeric(9,2) DEFAULT 0.00 NOT NULL,
    id_workshop bigint,
    id_palestra bigint,
    id_curso bigint,
    id_minicurso bigint
);


ALTER TABLE public.inscrito OWNER TO postgres;

--
-- TOC entry 179 (class 1259 OID 47196)
-- Name: instrutor; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE instrutor (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL,
    valorinstrutor numeric(9,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.instrutor OWNER TO postgres;

--
-- TOC entry 175 (class 1259 OID 47091)
-- Name: local; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE local (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_lugar bigint NOT NULL,
    valorlocacao numeric(9,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.local OWNER TO postgres;

--
-- TOC entry 189 (class 1259 OID 47433)
-- Name: logevento; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE logevento (
    id bigint NOT NULL,
    id_usuario bigint,
    evento character varying(255),
    descricao character varying(5000)
);


ALTER TABLE public.logevento OWNER TO postgres;

--
-- TOC entry 173 (class 1259 OID 47054)
-- Name: lugar; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lugar (
    id bigint NOT NULL,
    razaosocial character varying(255) NOT NULL,
    fantasia character varying(255) NOT NULL,
    tipodocumento character varying(10) NOT NULL,
    documento character varying(20) NOT NULL,
    cep character varying(10),
    endereco character varying(255),
    bairro character varying(100),
    cidade character varying(50),
    estado character varying(2),
    telefone character varying(20),
    fax character varying(20),
    numero character varying(10),
    complemento character varying(100)
);


ALTER TABLE public.lugar OWNER TO postgres;

--
-- TOC entry 174 (class 1259 OID 47078)
-- Name: lugarespaco; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE lugarespaco (
    id bigint NOT NULL,
    id_lugar bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    capacidade integer,
    complemento character varying(255)
);


ALTER TABLE public.lugarespaco OWNER TO postgres;

--
-- TOC entry 187 (class 1259 OID 47345)
-- Name: minicurso; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE minicurso (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_instrutor bigint NOT NULL,
    id_lugarespaco bigint NOT NULL
);


ALTER TABLE public.minicurso OWNER TO postgres;

--
-- TOC entry 178 (class 1259 OID 47178)
-- Name: orador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE orador (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL,
    valororador numeric(9,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.orador OWNER TO postgres;

--
-- TOC entry 183 (class 1259 OID 47266)
-- Name: organizador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE organizador (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL
);


ALTER TABLE public.organizador OWNER TO postgres;

--
-- TOC entry 185 (class 1259 OID 47301)
-- Name: palestra; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE palestra (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_palestrante bigint NOT NULL,
    id_lugarespaco bigint NOT NULL
);


ALTER TABLE public.palestra OWNER TO postgres;

--
-- TOC entry 180 (class 1259 OID 47214)
-- Name: palestrante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE palestrante (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL,
    valorpalestrante numeric(9,2) DEFAULT 0.00 NOT NULL
);


ALTER TABLE public.palestrante OWNER TO postgres;

--
-- TOC entry 170 (class 1259 OID 46740)
-- Name: participante; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE participante (
    id bigint NOT NULL,
    razaosocial character varying(255) NOT NULL,
    fantasia character varying(255) NOT NULL,
    tipodocumento character varying(10) NOT NULL,
    documento character varying(20) NOT NULL,
    cep character varying(10),
    endereco character varying(255),
    bairro character varying(100),
    cidade character varying(100),
    estado character varying(2),
    telefone character varying(20),
    fax character varying(20),
    celular character varying(20),
    twitter character varying(255),
    facebook character varying(255),
    linkedin character varying(255),
    googleplus character varying(255),
    numero character varying DEFAULT 10 NOT NULL,
    complemento character varying(100) NOT NULL
);


ALTER TABLE public.participante OWNER TO postgres;

--
-- TOC entry 177 (class 1259 OID 47155)
-- Name: patrocinador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE patrocinador (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL,
    id_patrocinio bigint NOT NULL,
    valorpatrocinio numeric(9,2) DEFAULT 0 NOT NULL
);


ALTER TABLE public.patrocinador OWNER TO postgres;

--
-- TOC entry 176 (class 1259 OID 47144)
-- Name: patrocinio; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE patrocinio (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    descricao character varying(255) NOT NULL,
    valorpatrocinio numeric(9,2) DEFAULT 0 NOT NULL
);


ALTER TABLE public.patrocinio OWNER TO postgres;

--
-- TOC entry 182 (class 1259 OID 47249)
-- Name: realizador; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE realizador (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_participante bigint NOT NULL
);


ALTER TABLE public.realizador OWNER TO postgres;

--
-- TOC entry 171 (class 1259 OID 46891)
-- Name: usuario; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE usuario (
    id bigint NOT NULL,
    id_participante bigint NOT NULL,
    email character varying(255) NOT NULL,
    senha character varying(32) NOT NULL,
    ativo boolean DEFAULT true NOT NULL,
    organizador boolean DEFAULT false NOT NULL,
    datacadastro timestamp with time zone NOT NULL,
    datavalidade timestamp with time zone NOT NULL
);


ALTER TABLE public.usuario OWNER TO postgres;

--
-- TOC entry 169 (class 1259 OID 46734)
-- Name: versao; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE versao (
    id bigint NOT NULL,
    numero character varying(20) DEFAULT ''::character varying NOT NULL
);


ALTER TABLE public.versao OWNER TO postgres;

--
-- TOC entry 188 (class 1259 OID 47365)
-- Name: workshop; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE workshop (
    id bigint NOT NULL,
    id_evento bigint NOT NULL,
    id_orador bigint NOT NULL,
    id_lugarespaco bigint NOT NULL
);


ALTER TABLE public.workshop OWNER TO postgres;

--
-- TOC entry 2053 (class 2606 OID 47236)
-- Name: pk_apoiador; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY apoiador
    ADD CONSTRAINT pk_apoiador PRIMARY KEY (id);


--
-- TOC entry 2071 (class 2606 OID 47329)
-- Name: pk_curso; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT pk_curso PRIMARY KEY (id);


--
-- TOC entry 2023 (class 2606 OID 46925)
-- Name: pk_evento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY evento
    ADD CONSTRAINT pk_evento PRIMARY KEY (id);


--
-- TOC entry 2065 (class 2606 OID 47288)
-- Name: pk_inscrito; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT pk_inscrito PRIMARY KEY (id);


--
-- TOC entry 2045 (class 2606 OID 47201)
-- Name: pk_instrutor; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY instrutor
    ADD CONSTRAINT pk_instrutor PRIMARY KEY (id);


--
-- TOC entry 2031 (class 2606 OID 47096)
-- Name: pk_local; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT pk_local PRIMARY KEY (id);


--
-- TOC entry 2077 (class 2606 OID 47440)
-- Name: pk_logevento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY logevento
    ADD CONSTRAINT pk_logevento PRIMARY KEY (id);


--
-- TOC entry 2025 (class 2606 OID 47061)
-- Name: pk_lugar; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lugar
    ADD CONSTRAINT pk_lugar PRIMARY KEY (id);


--
-- TOC entry 2029 (class 2606 OID 47085)
-- Name: pk_lugarespaco; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lugarespaco
    ADD CONSTRAINT pk_lugarespaco PRIMARY KEY (id);


--
-- TOC entry 2073 (class 2606 OID 47349)
-- Name: pk_minicurso; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY minicurso
    ADD CONSTRAINT pk_minicurso PRIMARY KEY (id);


--
-- TOC entry 2041 (class 2606 OID 47183)
-- Name: pk_orador; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orador
    ADD CONSTRAINT pk_orador PRIMARY KEY (id);


--
-- TOC entry 2061 (class 2606 OID 47270)
-- Name: pk_organizador; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organizador
    ADD CONSTRAINT pk_organizador PRIMARY KEY (id);


--
-- TOC entry 2069 (class 2606 OID 47305)
-- Name: pk_palestra; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY palestra
    ADD CONSTRAINT pk_palestra PRIMARY KEY (id);


--
-- TOC entry 2049 (class 2606 OID 47219)
-- Name: pk_palestrante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY palestrante
    ADD CONSTRAINT pk_palestrante PRIMARY KEY (id);


--
-- TOC entry 2015 (class 2606 OID 46747)
-- Name: pk_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY participante
    ADD CONSTRAINT pk_participante PRIMARY KEY (id);


--
-- TOC entry 2037 (class 2606 OID 47160)
-- Name: pk_patrocinador; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patrocinador
    ADD CONSTRAINT pk_patrocinador PRIMARY KEY (id);


--
-- TOC entry 2035 (class 2606 OID 47149)
-- Name: pk_patrocinio; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patrocinio
    ADD CONSTRAINT pk_patrocinio PRIMARY KEY (id);


--
-- TOC entry 2057 (class 2606 OID 47253)
-- Name: pk_realizador; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY realizador
    ADD CONSTRAINT pk_realizador PRIMARY KEY (id);


--
-- TOC entry 2019 (class 2606 OID 46897)
-- Name: pk_usuario; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT pk_usuario PRIMARY KEY (id);


--
-- TOC entry 2013 (class 2606 OID 46739)
-- Name: pk_versao; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY versao
    ADD CONSTRAINT pk_versao PRIMARY KEY (id);


--
-- TOC entry 2075 (class 2606 OID 47369)
-- Name: pk_workshop; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY workshop
    ADD CONSTRAINT pk_workshop PRIMARY KEY (id);


--
-- TOC entry 2055 (class 2606 OID 47238)
-- Name: uk_apoiador_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY apoiador
    ADD CONSTRAINT uk_apoiador_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2067 (class 2606 OID 47290)
-- Name: uk_inscrito_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT uk_inscrito_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2047 (class 2606 OID 47203)
-- Name: uk_instrutor_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY instrutor
    ADD CONSTRAINT uk_instrutor_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2033 (class 2606 OID 47098)
-- Name: uk_local_evento_lugar; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY local
    ADD CONSTRAINT uk_local_evento_lugar UNIQUE (id_evento, id_lugar);


--
-- TOC entry 2027 (class 2606 OID 47063)
-- Name: uk_lugar_documento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY lugar
    ADD CONSTRAINT uk_lugar_documento UNIQUE (documento);


--
-- TOC entry 2043 (class 2606 OID 47185)
-- Name: uk_orador_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY orador
    ADD CONSTRAINT uk_orador_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2063 (class 2606 OID 47272)
-- Name: uk_organizador_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY organizador
    ADD CONSTRAINT uk_organizador_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2051 (class 2606 OID 47221)
-- Name: uk_palestrante_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY palestrante
    ADD CONSTRAINT uk_palestrante_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2017 (class 2606 OID 46749)
-- Name: uk_participante_documento; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY participante
    ADD CONSTRAINT uk_participante_documento UNIQUE (documento);


--
-- TOC entry 2039 (class 2606 OID 47162)
-- Name: uk_patrocinador_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY patrocinador
    ADD CONSTRAINT uk_patrocinador_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2059 (class 2606 OID 47255)
-- Name: uk_realizador_evento_participante; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY realizador
    ADD CONSTRAINT uk_realizador_evento_participante UNIQUE (id_evento, id_participante);


--
-- TOC entry 2021 (class 2606 OID 46899)
-- Name: uk_usuario_email; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT uk_usuario_email UNIQUE (email);


--
-- TOC entry 2093 (class 2606 OID 47239)
-- Name: fk_apoiador_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY apoiador
    ADD CONSTRAINT fk_apoiador_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2094 (class 2606 OID 47244)
-- Name: fk_apoiador_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY apoiador
    ADD CONSTRAINT fk_apoiador_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2108 (class 2606 OID 47330)
-- Name: fk_curso_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT fk_curso_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2110 (class 2606 OID 47340)
-- Name: fk_curso_instrutor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT fk_curso_instrutor FOREIGN KEY (id_instrutor) REFERENCES instrutor(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2109 (class 2606 OID 47335)
-- Name: fk_curso_lugarespaco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY curso
    ADD CONSTRAINT fk_curso_lugarespaco FOREIGN KEY (id_lugarespaco) REFERENCES lugarespaco(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2079 (class 2606 OID 47500)
-- Name: fk_evento_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY evento
    ADD CONSTRAINT fk_evento_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2103 (class 2606 OID 47405)
-- Name: fk_inscrito_curso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_curso FOREIGN KEY (id_curso) REFERENCES curso(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2099 (class 2606 OID 47385)
-- Name: fk_inscrito_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2104 (class 2606 OID 47410)
-- Name: fk_inscrito_minicurso; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_minicurso FOREIGN KEY (id_minicurso) REFERENCES minicurso(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2102 (class 2606 OID 47400)
-- Name: fk_inscrito_palestra; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_palestra FOREIGN KEY (id_palestra) REFERENCES palestra(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2100 (class 2606 OID 47390)
-- Name: fk_inscrito_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2101 (class 2606 OID 47395)
-- Name: fk_inscrito_workshop; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY inscrito
    ADD CONSTRAINT fk_inscrito_workshop FOREIGN KEY (id_workshop) REFERENCES workshop(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2089 (class 2606 OID 47204)
-- Name: fk_instrutor_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instrutor
    ADD CONSTRAINT fk_instrutor_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2090 (class 2606 OID 47209)
-- Name: fk_instrutor_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY instrutor
    ADD CONSTRAINT fk_instrutor_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2081 (class 2606 OID 47099)
-- Name: fk_local_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local
    ADD CONSTRAINT fk_local_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2082 (class 2606 OID 47104)
-- Name: fk_local_lugar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY local
    ADD CONSTRAINT fk_local_lugar FOREIGN KEY (id_lugar) REFERENCES lugar(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2080 (class 2606 OID 47086)
-- Name: fk_lugarespaco_lugar; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY lugarespaco
    ADD CONSTRAINT fk_lugarespaco_lugar FOREIGN KEY (id_lugar) REFERENCES lugar(id) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- TOC entry 2111 (class 2606 OID 47350)
-- Name: fk_minicurso_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY minicurso
    ADD CONSTRAINT fk_minicurso_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2113 (class 2606 OID 47360)
-- Name: fk_minicurso_instrutor; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY minicurso
    ADD CONSTRAINT fk_minicurso_instrutor FOREIGN KEY (id_instrutor) REFERENCES instrutor(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2112 (class 2606 OID 47355)
-- Name: fk_minicurso_lugarespaco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY minicurso
    ADD CONSTRAINT fk_minicurso_lugarespaco FOREIGN KEY (id_lugarespaco) REFERENCES lugarespaco(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2087 (class 2606 OID 47186)
-- Name: fk_orador_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orador
    ADD CONSTRAINT fk_orador_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2088 (class 2606 OID 47191)
-- Name: fk_orador_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY orador
    ADD CONSTRAINT fk_orador_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2097 (class 2606 OID 47273)
-- Name: fk_organizador_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organizador
    ADD CONSTRAINT fk_organizador_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2098 (class 2606 OID 47278)
-- Name: fk_organizador_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY organizador
    ADD CONSTRAINT fk_organizador_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2105 (class 2606 OID 47306)
-- Name: fk_palestra_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY palestra
    ADD CONSTRAINT fk_palestra_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2107 (class 2606 OID 47316)
-- Name: fk_palestra_lugarespaco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY palestra
    ADD CONSTRAINT fk_palestra_lugarespaco FOREIGN KEY (id_lugarespaco) REFERENCES lugarespaco(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2106 (class 2606 OID 47311)
-- Name: fk_palestra_palestrante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY palestra
    ADD CONSTRAINT fk_palestra_palestrante FOREIGN KEY (id_palestrante) REFERENCES palestrante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2091 (class 2606 OID 47222)
-- Name: fk_palestrante_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY palestrante
    ADD CONSTRAINT fk_palestrante_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2092 (class 2606 OID 47227)
-- Name: fk_palestrante_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY palestrante
    ADD CONSTRAINT fk_palestrante_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2084 (class 2606 OID 47163)
-- Name: fk_patrocinador_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patrocinador
    ADD CONSTRAINT fk_patrocinador_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2085 (class 2606 OID 47168)
-- Name: fk_patrocinador_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patrocinador
    ADD CONSTRAINT fk_patrocinador_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2086 (class 2606 OID 47173)
-- Name: fk_patrocinador_patrocinio; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patrocinador
    ADD CONSTRAINT fk_patrocinador_patrocinio FOREIGN KEY (id_patrocinio) REFERENCES patrocinio(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2083 (class 2606 OID 47150)
-- Name: fk_patrocinio_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY patrocinio
    ADD CONSTRAINT fk_patrocinio_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2095 (class 2606 OID 47256)
-- Name: fk_realizador_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY realizador
    ADD CONSTRAINT fk_realizador_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2096 (class 2606 OID 47261)
-- Name: fk_realizador_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY realizador
    ADD CONSTRAINT fk_realizador_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2078 (class 2606 OID 47495)
-- Name: fk_usuario_participante; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT fk_usuario_participante FOREIGN KEY (id_participante) REFERENCES participante(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2114 (class 2606 OID 47370)
-- Name: fk_workshop_evento; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY workshop
    ADD CONSTRAINT fk_workshop_evento FOREIGN KEY (id_evento) REFERENCES evento(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2115 (class 2606 OID 47375)
-- Name: fk_workshop_lugarespaco; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY workshop
    ADD CONSTRAINT fk_workshop_lugarespaco FOREIGN KEY (id_lugarespaco) REFERENCES lugarespaco(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2116 (class 2606 OID 47380)
-- Name: fk_workshop_orador; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY workshop
    ADD CONSTRAINT fk_workshop_orador FOREIGN KEY (id_orador) REFERENCES instrutor(id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2123 (class 0 OID 0)
-- Dependencies: 5
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2013-11-16 14:20:44

--
-- PostgreSQL database dump complete
--

