--
-- PostgreSQL database dump
--

-- Dumped from database version 15.2
-- Dumped by pg_dump version 15.2

-- Started on 2023-06-20 06:09:59

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 218 (class 1259 OID 16430)
-- Name: cluster; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.cluster (
    id integer NOT NULL,
    title text NOT NULL,
    description text
);


ALTER TABLE public.cluster OWNER TO postgres;

--
-- TOC entry 219 (class 1259 OID 16437)
-- Name: cluster_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.cluster ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.cluster_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 230 (class 1259 OID 24623)
-- Name: comment; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.comment (
    id integer NOT NULL,
    id_user integer NOT NULL,
    date date NOT NULL,
    text text NOT NULL,
    id_request integer
);


ALTER TABLE public.comment OWNER TO postgres;

--
-- TOC entry 233 (class 1259 OID 24701)
-- Name: comment_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.comment ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.comment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 226 (class 1259 OID 16465)
-- Name: importance; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.importance (
    id integer NOT NULL,
    title text NOT NULL,
    description text
);


ALTER TABLE public.importance OWNER TO postgres;

--
-- TOC entry 227 (class 1259 OID 16472)
-- Name: importance_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.importance ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.importance_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 216 (class 1259 OID 16420)
-- Name: menu; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.menu (
    id integer NOT NULL,
    title text NOT NULL,
    link text NOT NULL
);


ALTER TABLE public.menu OWNER TO postgres;

--
-- TOC entry 234 (class 1259 OID 24712)
-- Name: menuRole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."menuRole" (
    id integer NOT NULL,
    id_menu integer NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE public."menuRole" OWNER TO postgres;

--
-- TOC entry 235 (class 1259 OID 24727)
-- Name: menuRole_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."menuRole" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public."menuRole_id_seq"
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 217 (class 1259 OID 16427)
-- Name: menu_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.menu ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.menu_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 220 (class 1259 OID 16438)
-- Name: module; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.module (
    id integer NOT NULL,
    title text NOT NULL,
    description text,
    id_cluster integer NOT NULL
);


ALTER TABLE public.module OWNER TO postgres;

--
-- TOC entry 221 (class 1259 OID 16441)
-- Name: module_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.module ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.module_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 231 (class 1259 OID 24645)
-- Name: request; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.request (
    id integer NOT NULL,
    id_module integer NOT NULL,
    id_status integer NOT NULL,
    date_create date NOT NULL,
    date_update date NOT NULL,
    id_user integer NOT NULL,
    id_user_responsible integer NOT NULL,
    id_user_observer integer NOT NULL,
    id_importance integer NOT NULL
);


ALTER TABLE public.request OWNER TO postgres;

--
-- TOC entry 232 (class 1259 OID 24700)
-- Name: request_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.request ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.request_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 222 (class 1259 OID 16449)
-- Name: role; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.role (
    id integer NOT NULL,
    title text NOT NULL,
    description text,
    basic boolean DEFAULT false
);


ALTER TABLE public.role OWNER TO postgres;

--
-- TOC entry 223 (class 1259 OID 16456)
-- Name: role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.role ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 225 (class 1259 OID 16458)
-- Name: status; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.status (
    id integer NOT NULL,
    title text NOT NULL,
    description text
);


ALTER TABLE public.status OWNER TO postgres;

--
-- TOC entry 224 (class 1259 OID 16457)
-- Name: status_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public.status ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.status_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 214 (class 1259 OID 16403)
-- Name: user; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."user" (
    id integer NOT NULL,
    login text NOT NULL,
    password text NOT NULL,
    username text,
    birthdate date
);


ALTER TABLE public."user" OWNER TO postgres;

--
-- TOC entry 229 (class 1259 OID 16474)
-- Name: userRole; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public."userRole" (
    id integer NOT NULL,
    id_user integer NOT NULL,
    id_role integer NOT NULL
);


ALTER TABLE public."userRole" OWNER TO postgres;

--
-- TOC entry 228 (class 1259 OID 16473)
-- Name: user_role_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."userRole" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 215 (class 1259 OID 16417)
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

ALTER TABLE public."user" ALTER COLUMN id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);


--
-- TOC entry 3409 (class 0 OID 16430)
-- Dependencies: 218
-- Data for Name: cluster; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.cluster (id, title, description) FROM stdin;
1	Проекты	\N
2	Отделы	\N
3	Услуги	\N
\.


--
-- TOC entry 3421 (class 0 OID 24623)
-- Dependencies: 230
-- Data for Name: comment; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.comment (id, id_user, date, text, id_request) FROM stdin;
1	4	2003-01-01	Сломалось	4
2	34	2023-06-11	фоарофыарлоф рфал оф	7
3	32	2023-06-11	wf f wf wf	8
4	30	2023-06-11	htrhtrhr hrth rt	9
5	30	2023-06-11	Даник лапулька)	10
6	27	2023-06-19	Модуль не работает	11
\.


--
-- TOC entry 3417 (class 0 OID 16465)
-- Dependencies: 226
-- Data for Name: importance; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.importance (id, title, description) FROM stdin;
1	Не срочно	\N
2	Средняя срочность	\N
3	Срочно	\N
4	Очень срочно	\N
\.


--
-- TOC entry 3407 (class 0 OID 16420)
-- Dependencies: 216
-- Data for Name: menu; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.menu (id, title, link) FROM stdin;
1	Главная	/
4	Профиль	ауц
3	Пользователи	уца
5	Роли	пу
7	Отделы	DS
2	Заявки	createRequest
\.


--
-- TOC entry 3425 (class 0 OID 24712)
-- Dependencies: 234
-- Data for Name: menuRole; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."menuRole" (id, id_menu, id_role) FROM stdin;
1	1	1
3	3	2
5	3	1
\.


--
-- TOC entry 3411 (class 0 OID 16438)
-- Dependencies: 220
-- Data for Name: module; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.module (id, title, description, id_cluster) FROM stdin;
1	Росдистант	\N	1
2	Образовательный портал	\N	1
4	Отдел тестирования	\N	2
5	Деканат	\N	2
6	Документы	\N	3
\.


--
-- TOC entry 3422 (class 0 OID 24645)
-- Dependencies: 231
-- Data for Name: request; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.request (id, id_module, id_status, date_create, date_update, id_user, id_user_responsible, id_user_observer, id_importance) FROM stdin;
4	1	1	2002-01-01	2003-02-02	3	4	5	1
5	4	1	2023-06-11	2023-06-11	34	34	34	4
6	2	1	2023-06-11	2023-06-11	34	34	34	3
7	6	1	2023-06-11	2023-06-11	34	34	34	2
8	5	1	2023-06-11	2023-06-11	32	32	32	1
9	4	1	2023-06-11	2023-06-11	30	23	23	4
10	6	1	2023-06-11	2023-06-11	30	23	23	3
11	1	1	2023-06-19	2023-06-19	27	23	23	4
\.


--
-- TOC entry 3413 (class 0 OID 16449)
-- Dependencies: 222
-- Data for Name: role; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.role (id, title, description, basic) FROM stdin;
2	Руководитель	\N	t
3	Специалист	\N	t
4	Студент	\N	t
1	Администратор	\N	f
\.


--
-- TOC entry 3416 (class 0 OID 16458)
-- Dependencies: 225
-- Data for Name: status; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.status (id, title, description) FROM stdin;
1	Новая заявка	Заявка поступившая в систему
2	Открытая заявка	Заявка взятая в работу (назначен ответственный)
3	Закрытая заявка	Заявка закрытая ответственным за неё специалистом
4	Повторно открытая заявка	Возвращённая на доработку ранее завершённая заявка 
5	Подтверждённая заявка	Заявка полностью исполненная 
6	Делегирование заявки	Смена исполнителя 
\.


--
-- TOC entry 3405 (class 0 OID 16403)
-- Dependencies: 214
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."user" (id, login, password, username, birthdate) FROM stdin;
3	testL	testP	testU	2023-05-22
2	testL_1	testP_2	testU	2023-05-22
5	ada	adasd	SsADASD	2023-05-05
9	testLe	ere	wreew	2023-05-05
13	werwer	werwerwe	wwerewr	2023-05-04
4	Antonenko1999	awd	Antonenko1999	2023-05-12
14	ergregergreeegeger	ergerg	gergrege	2023-05-05
15	qwdq	qwdqwdqd	awdawdadad	2023-06-07
16	qwdqwerw	qwdwrewqwdqd	awdawwerdadad	2023-06-07
18	jtgjhgj	gjghjhgj	ghjhgjhg	2023-06-03
19	grdgrdgdrgrd	rgrd	rdgrdgdrgd	2023-06-09
20	fdhfdhdfhdf	hdfhfdhdfhfdhfdh	ddfhfdhdhf	2023-06-09
21	fhfghfgh	fghfhgfhgfhf	fghfhfhfghfg	2023-06-09
22	bnmbnmb	mbnmbmb	mnbmnbm	2023-06-23
25	ÐÐ½Ð´ÑÐµÐ¹	ÐÐ½ÑÐ¾Ð½ÐµÐ½ÐºÐ¾	Ð¡ÐµÑÐ³ÐµÐµÐ²Ð¸Ñ	2023-06-03
28	Ð¡ÑÐµÑÐº	ÐÐ°Ð°Ð¿ÑÐ²	ÐÐ°Ð½Ð¸Ðº	2023-06-11
32	Даниил	фывфвф	Ана ау 	2023-06-09
34	рвкруру	рукрукрукрукрук	фцвйфцвцйвйв	2023-06-25
23	asdada	asdasdas	adasdadasdad	2023-06-23
30	Андрей1	Антоненко	ффапфафа	2023-06-10
27	Андрей	Антоненко	Андрей Сергеевич	2023-06-07
35	12345	12345	12345	2023-06-11
\.


--
-- TOC entry 3420 (class 0 OID 16474)
-- Dependencies: 229
-- Data for Name: userRole; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public."userRole" (id, id_user, id_role) FROM stdin;
7	23	2
8	25	3
9	28	2
10	30	4
11	32	3
12	34	3
6	23	1
13	35	3
\.


--
-- TOC entry 3432 (class 0 OID 0)
-- Dependencies: 219
-- Name: cluster_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.cluster_id_seq', 3, true);


--
-- TOC entry 3433 (class 0 OID 0)
-- Dependencies: 233
-- Name: comment_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.comment_id_seq', 6, true);


--
-- TOC entry 3434 (class 0 OID 0)
-- Dependencies: 227
-- Name: importance_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.importance_id_seq', 4, true);


--
-- TOC entry 3435 (class 0 OID 0)
-- Dependencies: 235
-- Name: menuRole_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public."menuRole_id_seq"', 5, true);


--
-- TOC entry 3436 (class 0 OID 0)
-- Dependencies: 217
-- Name: menu_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.menu_id_seq', 7, true);


--
-- TOC entry 3437 (class 0 OID 0)
-- Dependencies: 221
-- Name: module_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.module_id_seq', 6, true);


--
-- TOC entry 3438 (class 0 OID 0)
-- Dependencies: 232
-- Name: request_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.request_id_seq', 11, true);


--
-- TOC entry 3439 (class 0 OID 0)
-- Dependencies: 223
-- Name: role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.role_id_seq', 4, true);


--
-- TOC entry 3440 (class 0 OID 0)
-- Dependencies: 224
-- Name: status_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.status_id_seq', 6, true);


--
-- TOC entry 3441 (class 0 OID 0)
-- Dependencies: 228
-- Name: user_role_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.user_role_id_seq', 20, true);


--
-- TOC entry 3442 (class 0 OID 0)
-- Dependencies: 215
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.users_id_seq', 39, true);


--
-- TOC entry 3233 (class 2606 OID 16436)
-- Name: cluster cluster_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.cluster
    ADD CONSTRAINT cluster_pkey PRIMARY KEY (id);


--
-- TOC entry 3245 (class 2606 OID 24629)
-- Name: comment comment_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_pkey PRIMARY KEY (id);


--
-- TOC entry 3241 (class 2606 OID 16471)
-- Name: importance importance_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.importance
    ADD CONSTRAINT importance_pkey PRIMARY KEY (id);


--
-- TOC entry 3229 (class 2606 OID 16429)
-- Name: menu link; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT link UNIQUE (link);


--
-- TOC entry 3225 (class 2606 OID 16419)
-- Name: user login; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT login UNIQUE (login);


--
-- TOC entry 3249 (class 2606 OID 24729)
-- Name: menuRole menuRole_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."menuRole"
    ADD CONSTRAINT "menuRole_pkey" PRIMARY KEY (id);


--
-- TOC entry 3231 (class 2606 OID 16426)
-- Name: menu menu_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);


--
-- TOC entry 3235 (class 2606 OID 16448)
-- Name: module module_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module
    ADD CONSTRAINT module_pkey PRIMARY KEY (id);


--
-- TOC entry 3247 (class 2606 OID 24649)
-- Name: request request_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_pkey PRIMARY KEY (id);


--
-- TOC entry 3237 (class 2606 OID 16455)
-- Name: role role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.role
    ADD CONSTRAINT role_pkey PRIMARY KEY (id);


--
-- TOC entry 3239 (class 2606 OID 16464)
-- Name: status status_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);


--
-- TOC entry 3243 (class 2606 OID 16490)
-- Name: userRole user_role_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."userRole"
    ADD CONSTRAINT user_role_pkey PRIMARY KEY (id);


--
-- TOC entry 3227 (class 2606 OID 16410)
-- Name: user users_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- TOC entry 3253 (class 2606 OID 24702)
-- Name: comment comment_id_request_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_id_request_fkey FOREIGN KEY (id_request) REFERENCES public.request(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3254 (class 2606 OID 24640)
-- Name: comment comment_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.comment
    ADD CONSTRAINT comment_id_user_fkey FOREIGN KEY (id_user) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3251 (class 2606 OID 16484)
-- Name: userRole fk_role; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."userRole"
    ADD CONSTRAINT fk_role FOREIGN KEY (id_role) REFERENCES public.role(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3252 (class 2606 OID 16479)
-- Name: userRole fk_user; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."userRole"
    ADD CONSTRAINT fk_user FOREIGN KEY (id_user) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3261 (class 2606 OID 24717)
-- Name: menuRole menuRole_id_menu_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."menuRole"
    ADD CONSTRAINT "menuRole_id_menu_fkey" FOREIGN KEY (id_menu) REFERENCES public.menu(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3262 (class 2606 OID 24722)
-- Name: menuRole menuRole_id_role_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public."menuRole"
    ADD CONSTRAINT "menuRole_id_role_fkey" FOREIGN KEY (id_role) REFERENCES public.role(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3250 (class 2606 OID 24730)
-- Name: module module_id_cluster_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.module
    ADD CONSTRAINT module_id_cluster_fkey FOREIGN KEY (id_cluster) REFERENCES public.cluster(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3255 (class 2606 OID 24707)
-- Name: request request_id_importance_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_importance_fkey FOREIGN KEY (id_importance) REFERENCES public.importance(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3256 (class 2606 OID 24655)
-- Name: request request_id_module_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_module_fkey FOREIGN KEY (id_module) REFERENCES public.module(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3257 (class 2606 OID 24680)
-- Name: request request_id_status_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_status_fkey FOREIGN KEY (id_status) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3258 (class 2606 OID 24685)
-- Name: request request_id_user_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_user_fkey FOREIGN KEY (id_user) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3259 (class 2606 OID 24695)
-- Name: request request_id_user_observer_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_user_observer_fkey FOREIGN KEY (id_user_observer) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


--
-- TOC entry 3260 (class 2606 OID 24690)
-- Name: request request_id_user_responsible_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.request
    ADD CONSTRAINT request_id_user_responsible_fkey FOREIGN KEY (id_user_responsible) REFERENCES public."user"(id) ON UPDATE CASCADE ON DELETE CASCADE NOT VALID;


-- Completed on 2023-06-20 06:10:00

--
-- PostgreSQL database dump complete
--

