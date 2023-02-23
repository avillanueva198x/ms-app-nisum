insert  into application_parameter (code,description,value) values ('ORDER_DATE_OFFSET','Margen de tiempo en segundos para buscar las ultimas ordenes','0');
insert  into application_parameter (code,description,value) values ('ORDER_MAX_DELIVERY_TIME','Tiempo de entrega máximo (en minutos)','90');
insert  into application_parameter (code,description,value) values ('ORDER_MAX_RETRIES','Numero máximo de reintentos para insertar la orden','3');
insert  into application_parameter (code,description,value) values ('ORDER_SERVICE_URL','Dirección de donde se obtienen las ordenes','http://200.37.146.169:9484');
insert  into application_parameter (code,description,value) values ('ORDER_SYNC_ACTIVE','Activa el llamado de ordenes de call center','FALSE');
insert  into application_parameter (code,description,value) values ('SEND_ORDER_WITHOUT_GEO_COORD','Activa el envío de órdenes de call al dispatcher','TRUE');
insert  into application_parameter (code,description,value) values ('TIME_OUT_DISPATCHER','Time out en milisegundos para DD del callwatcher','120000');
insert  into application_parameter (code,description,value) values ('TIME_OUT_INSINK','Time out en milisegundos para insink de callwatcher','120000');

insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('925','925','2019-03-10 11:41:31','RT','N',NULL,'BTL_CENTER');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('969','969','2018-07-28 07:30:05','IKF','N',NULL,'IKF_DELIVERY');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('A57','A57','2020-04-03 10:32:17','RT','N',NULL,'BTL_CENTER');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('A58','A58','2019-03-13 16:46:56','RT','N',NULL,'BTL_CENTER');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('B88','B88','2019-08-07 15:41:24','IKF','N',NULL,'IKF_DELIVERY');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('E05','E05','2020-03-13 13:54:04','IKF','N',NULL,'UNIFIED_DELIVERY');
insert  into drugstore_mapping (drugstore_code,inkaventa_code,last_evaluated_date,company,enabled,status,callsource`) values ('E31','E31','2018-07-28 07:30:05','IKF','N',NULL,'BTL_CENTER');

