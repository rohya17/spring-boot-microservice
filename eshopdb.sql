PGDMP                      |            eshop    16.4    16.4 .               0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                        0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            !           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            "           1262    16394    eshop    DATABASE     �   CREATE DATABASE eshop WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE eshop;
                postgres    false            �            1259    16395    cart    TABLE     �   CREATE TABLE public.cart (
    id integer NOT NULL,
    enduserid integer NOT NULL,
    productid integer NOT NULL,
    quantity integer NOT NULL
);
    DROP TABLE public.cart;
       public         heap    postgres    false            �            1259    16398    cart_id_seq    SEQUENCE     �   CREATE SEQUENCE public.cart_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.cart_id_seq;
       public          postgres    false    215            #           0    0    cart_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.cart_id_seq OWNED BY public.cart.id;
          public          postgres    false    216            �            1259    16399    enduser    TABLE     �   CREATE TABLE public.enduser (
    id integer NOT NULL,
    goodname character varying(10) NOT NULL,
    email character varying(50) NOT NULL,
    password text NOT NULL,
    mobile character varying(10) NOT NULL
);
    DROP TABLE public.enduser;
       public         heap    postgres    false            �            1259    16404    enduser_id_seq    SEQUENCE     �   CREATE SEQUENCE public.enduser_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.enduser_id_seq;
       public          postgres    false    217            $           0    0    enduser_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.enduser_id_seq OWNED BY public.enduser.id;
          public          postgres    false    218            �            1259    16405    eshop    TABLE     �   CREATE TABLE public.eshop (
    id integer NOT NULL,
    eshopname character varying(20) NOT NULL,
    eshopdescription character varying(500) NOT NULL,
    eshopsearchtags character varying(500) NOT NULL,
    eshopcoverimage character varying(200)
);
    DROP TABLE public.eshop;
       public         heap    postgres    false            �            1259    16410 
   eshopowner    TABLE     �   CREATE TABLE public.eshopowner (
    id integer NOT NULL,
    goodname character varying(10) NOT NULL,
    email character varying(50) NOT NULL,
    password text NOT NULL,
    mobile character varying(10) NOT NULL,
    eshopid integer NOT NULL
);
    DROP TABLE public.eshopowner;
       public         heap    postgres    false            �            1259    16415    eshop_id_seq    SEQUENCE     �   CREATE SEQUENCE public.eshop_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 #   DROP SEQUENCE public.eshop_id_seq;
       public          postgres    false    220            %           0    0    eshop_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.eshop_id_seq OWNED BY public.eshopowner.id;
          public          postgres    false    221            �            1259    16416    eshop_id_seq1    SEQUENCE     �   CREATE SEQUENCE public.eshop_id_seq1
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.eshop_id_seq1;
       public          postgres    false    219            &           0    0    eshop_id_seq1    SEQUENCE OWNED BY     >   ALTER SEQUENCE public.eshop_id_seq1 OWNED BY public.eshop.id;
          public          postgres    false    222            �            1259    16417    orderdetails    TABLE     �  CREATE TABLE public.orderdetails (
    id integer NOT NULL,
    enduserid integer NOT NULL,
    productid integer NOT NULL,
    billingamount double precision NOT NULL,
    quantity integer,
    userratings integer,
    reviewcomment character varying(500),
    reviewimage character varying(200),
    cancelled boolean DEFAULT false NOT NULL,
    returned boolean DEFAULT false NOT NULL,
    dispatched boolean DEFAULT false NOT NULL,
    delivered boolean DEFAULT false NOT NULL
);
     DROP TABLE public.orderdetails;
       public         heap    postgres    false            �            1259    16426    product    TABLE     �  CREATE TABLE public.product (
    id integer NOT NULL,
    productname character varying(200) NOT NULL,
    productdescription character varying(500) NOT NULL,
    searchtags character varying(500) NOT NULL,
    productimage character varying(200) NOT NULL,
    eshopid integer NOT NULL,
    price double precision NOT NULL,
    outofstock boolean DEFAULT false NOT NULL,
    deleted boolean DEFAULT false NOT NULL,
    percentdiscount integer DEFAULT 0 NOT NULL
);
    DROP TABLE public.product;
       public         heap    postgres    false            �            1259    16434    product_id_seq    SEQUENCE     �   CREATE SEQUENCE public.product_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.product_id_seq;
       public          postgres    false    224            '           0    0    product_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.product_id_seq OWNED BY public.product.id;
          public          postgres    false    225            �            1259    16435    userproductdetails_id_seq    SEQUENCE     �   CREATE SEQUENCE public.userproductdetails_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 0   DROP SEQUENCE public.userproductdetails_id_seq;
       public          postgres    false    223            (           0    0    userproductdetails_id_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.userproductdetails_id_seq OWNED BY public.orderdetails.id;
          public          postgres    false    226            i           2604    16436    cart id    DEFAULT     b   ALTER TABLE ONLY public.cart ALTER COLUMN id SET DEFAULT nextval('public.cart_id_seq'::regclass);
 6   ALTER TABLE public.cart ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            j           2604    16437 
   enduser id    DEFAULT     h   ALTER TABLE ONLY public.enduser ALTER COLUMN id SET DEFAULT nextval('public.enduser_id_seq'::regclass);
 9   ALTER TABLE public.enduser ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            k           2604    16438    eshop id    DEFAULT     e   ALTER TABLE ONLY public.eshop ALTER COLUMN id SET DEFAULT nextval('public.eshop_id_seq1'::regclass);
 7   ALTER TABLE public.eshop ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    219            l           2604    16439    eshopowner id    DEFAULT     i   ALTER TABLE ONLY public.eshopowner ALTER COLUMN id SET DEFAULT nextval('public.eshop_id_seq'::regclass);
 <   ALTER TABLE public.eshopowner ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    221    220            m           2604    16440    orderdetails id    DEFAULT     x   ALTER TABLE ONLY public.orderdetails ALTER COLUMN id SET DEFAULT nextval('public.userproductdetails_id_seq'::regclass);
 >   ALTER TABLE public.orderdetails ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    223            r           2604    16441 
   product id    DEFAULT     h   ALTER TABLE ONLY public.product ALTER COLUMN id SET DEFAULT nextval('public.product_id_seq'::regclass);
 9   ALTER TABLE public.product ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    225    224                      0    16395    cart 
   TABLE DATA           B   COPY public.cart (id, enduserid, productid, quantity) FROM stdin;
    public          postgres    false    215   �3                 0    16399    enduser 
   TABLE DATA           H   COPY public.enduser (id, goodname, email, password, mobile) FROM stdin;
    public          postgres    false    217   �3                 0    16405    eshop 
   TABLE DATA           b   COPY public.eshop (id, eshopname, eshopdescription, eshopsearchtags, eshopcoverimage) FROM stdin;
    public          postgres    false    219   �4                 0    16410 
   eshopowner 
   TABLE DATA           T   COPY public.eshopowner (id, goodname, email, password, mobile, eshopid) FROM stdin;
    public          postgres    false    220   �5                 0    16417    orderdetails 
   TABLE DATA           �   COPY public.orderdetails (id, enduserid, productid, billingamount, quantity, userratings, reviewcomment, reviewimage, cancelled, returned, dispatched, delivered) FROM stdin;
    public          postgres    false    223   (7                 0    16426    product 
   TABLE DATA           �   COPY public.product (id, productname, productdescription, searchtags, productimage, eshopid, price, outofstock, deleted, percentdiscount) FROM stdin;
    public          postgres    false    224   E7       )           0    0    cart_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.cart_id_seq', 1, false);
          public          postgres    false    216            *           0    0    enduser_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.enduser_id_seq', 5, true);
          public          postgres    false    218            +           0    0    eshop_id_seq    SEQUENCE SET     :   SELECT pg_catalog.setval('public.eshop_id_seq', 6, true);
          public          postgres    false    221            ,           0    0    eshop_id_seq1    SEQUENCE SET     <   SELECT pg_catalog.setval('public.eshop_id_seq1', 19, true);
          public          postgres    false    222            -           0    0    product_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.product_id_seq', 1, false);
          public          postgres    false    225            .           0    0    userproductdetails_id_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.userproductdetails_id_seq', 1, false);
          public          postgres    false    226            w           2606    16443    cart cart_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.cart
    ADD CONSTRAINT cart_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.cart DROP CONSTRAINT cart_pkey;
       public            postgres    false    215            y           2606    16445    enduser enduser_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.enduser
    ADD CONSTRAINT enduser_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.enduser DROP CONSTRAINT enduser_pkey;
       public            postgres    false    217            }           2606    16447    eshopowner eshop_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.eshopowner
    ADD CONSTRAINT eshop_pkey PRIMARY KEY (id);
 ?   ALTER TABLE ONLY public.eshopowner DROP CONSTRAINT eshop_pkey;
       public            postgres    false    220            {           2606    24577    eshop eshop_pkey1 
   CONSTRAINT     O   ALTER TABLE ONLY public.eshop
    ADD CONSTRAINT eshop_pkey1 PRIMARY KEY (id);
 ;   ALTER TABLE ONLY public.eshop DROP CONSTRAINT eshop_pkey1;
       public            postgres    false    219            �           2606    16449    product product_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.product
    ADD CONSTRAINT product_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.product DROP CONSTRAINT product_pkey;
       public            postgres    false    224                       2606    16451 $   orderdetails userproductdetails_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.orderdetails
    ADD CONSTRAINT userproductdetails_pkey PRIMARY KEY (id);
 N   ALTER TABLE ONLY public.orderdetails DROP CONSTRAINT userproductdetails_pkey;
       public            postgres    false    223                  x������ � �         �   x�m͹�0  й��Z.��Q�& �R�Dn��_o�\�����r��a퇖���D,��"%����[!��.Ůw��4�K������w���!1�9�$+�^�	�*��b�7���1�n�΄� �uw5Q�[��)����1-u�Dwm(��it~�'�~��<�           x�͑�N�0D��W�$!ݤ!8 T�T�z��coZ��m�n����S�U/+�H3�V�����IDo���	��%	�ְSF�=��SzҌaG���A�A	
9����OLφ���^6��^~��f��w�dzx,9!!�|p���&���%���)����(/0b�I�U}b�X��৉*j���j,��}�;�I�l��U%z�qќ�f������}�^Q`G
S�9Nyp�S�n,���1=Oǣ	�юd�tH�9%2᭻�[������G�y�$?���A         g  x�u�Ks�0��u��#�@�NKA4*��"��F)Q�������ͷ<ϼG,����۩�qo��=h�qK�[ѧeZo<'�����u]��{�+V��tf!Y��M�CpD':��Հ*�`³m���g��)cQ��n��m��U��#y�c��Y�cC�a�urE��{5����3��-�1*>�U�����hy��.q�]x=�F�1��9\[��2g꫆%���3��m3��xMr��&Y��ӯ����&hO�#�p2��5�l<�mo���WM�t0�zZ��T/�s��B���+����V�|d!�S��5�p��'p��MB�/���ö��FE`�@Q���E(��%I�/ˣJ            x������ � �            x������ � �     