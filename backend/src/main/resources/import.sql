INSERT INTO "CLIENT_TYPE" ("ID_CLIENT_TYPE", "TYPE") VALUES (1, 'regular'), (2, 'business');
INSERT INTO "ADDRESS" ("ID_ADDRESS", "CITY", "COUNTRY", "POST_OFFICE", "STREET", "STREET_NUMBER", "ZIP_CODE") VALUES (1, 'Wroclaw', 'Poland', 'Wroclaw', 'Jana Dlugosza', '2-6', '51-162');
INSERT INTO "CLIENT_DATA" ("ID_CLIENT_DATA", "EMAIL_ADDRESS", "LOGIN", "NAME", "SURNAME", "ID_ADDRESS", "ID_CLIENT_TYPE") VALUES (1, 'asd@comarch.com', 'asd', 'Asd', 'Dsa', 1, 1);

INSERT INTO "PARAMETER" ("ID_PARAMETER", "NAME") VALUES (1, 'Speed');
INSERT INTO "PARAMETER_DETAIL" ("ID_PARAMETER_DETAIL", "ACTIVE_FROM", "ACTIVE_TO", "PRICE_BUSINESS", "PRICE_REGULAR", "VALUE", "ID_PARAMETER", "MONTHLY") VALUES (1, '2022-08-09', '2022-08-12', 49.99, 59.99, '1Gbps', 1, TRUE);

INSERT INTO "PARAMETER" ("ID_PARAMETER", "NAME") VALUES (2, 'Activation');
INSERT INTO "PARAMETER_DETAIL" ("ID_PARAMETER_DETAIL", "ACTIVE_FROM", "ACTIVE_TO", "PRICE_BUSINESS", "PRICE_REGULAR", "VALUE", "ID_PARAMETER", "MONTHLY") VALUES (2, '2022-08-09', '2022-08-12', 49.99, 59.99, 'Activation', 2, FALSE);

--INSERT INTO "ORDER" ("ID_ORDER", "ORDER_START_DATE", "ORDER_END_DATE", "ID_CLIENT_DATA", "ORDER_STATUS") VALUES (1, '2022-08-10', '2023-08-11', 1, 'Processed');

--INSERT INTO "ORDER_ITEM" ("ID_ORDER_ITEM", "ACTIVATION_DATE", "ORDER_ITEM_NAME", "STATUS", "ID_ORDER") VALUES (1, '2022-08-11', 'Fiber', 'Activated', 1);
--INSERT INTO "ORDER_ITEM_PARAMETER" ("ID_ORDER_ITEM_PARAMETER", "ID_ORDER_ITEM", "ID_PARAMETER_DETAIL", "PAYED") VALUES (1, 1, 1, TRUE);
--INSERT INTO "ORDER_ITEM_PARAMETER" ("ID_ORDER_ITEM_PARAMETER", "ID_ORDER_ITEM", "ID_PARAMETER_DETAIL", "PAYED") VALUES (2, 1, 2, FALSE);

--INSERT INTO "ORDER_ITEM" ("ID_ORDER_ITEM", "ACTIVATION_DATE", "ORDER_ITEM_NAME", "STATUS", "ID_ORDER") VALUES (2, '2022-08-11', 'Activation', 'Activated', 1);
--INSERT INTO "ORDER_ITEM_PARAMETER" ("ID_ORDER_ITEM_PARAMETER", "ID_ORDER_ITEM", "ID_PARAMETER_DETAIL", "PAYED") VALUES (3, 2, 2, TRUE);

INSERT INTO "PRODUCT" ("ID_PRODUCT", "PRODUCT_NAME") VALUES (1, 'Fiber 1'), (2, 'Fiber 2');
INSERT INTO "OFFER" ("ID_OFFER", "OFFER_NAME", "ID_PRODUCT") VALUES (1, 'Fiber 1 offer', 1), (2, 'Fiber 2 offer', 2);

INSERT INTO "PARAMETER_X_PRODUCT" ("ID_PARAMETER", "ID_PRODUCT") VALUES (1, 1), (2, 1), (2, 1), (2, 2);
