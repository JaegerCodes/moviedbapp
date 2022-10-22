# Principio de responsabilidad única
El principio de responsabilidad única consiste en que un objeto, clase, o módulo (Entre otros) debe ser creado con un único fin o, como lo describe el principio,
única responsabilidad. Por ejemplo, en el proyecto se utilizó una arquitectura orientada a módulos como features, en el cual el súbmodulo moviedetail_data su único fin
es entregar datos ya sean remotos o locales al submódulo moviedetail_domain.

# Un buen código limpio
Un buen código limpio es cuando este es fácilmente extendible, testiable, escalable y modificable. Así mismo, cuando, por ejemplo, entra un nuevo colaborador, este puede
adaptarse fácilmente al código fuente, y entienda el fin de una funcionalidad o, del mismo modo, las reglas de negocio.

# Lo que falta
Terminar de realizar los testing de las capas domain, para este caso de pruebas se hizo en los testings para todas las capas data de cada Feature Module y solo el unit
test de la capa moviedetail_domain

Agregar Android Instrumented Tests para poder testear las capas de presentación de cada módulo

El uso de Flavors para poder manejar (En este proyecto solo se simuló dos ambientes DEV Y PRD usando buildTypes en la capa core) los ambientes del negocio por ejemplo
uno de desarrollo "DEV", para pruebas "QA", para demo que se mostrará al cliente "UAT" y uno para producción "PRD". Además con esto se puede customizar mediante los
archivos gradle el nombre de la APP para que el equipo de testers pueda verificar fácilmente en qué ambiente se encuentra el APK a testear (Ej. MyAp QA).

Configurar ambientes de pruebas de integración continua mediante la creación de pipelines como jenkins entre los más clásicos, o BitRise para poder automatizar
las entregas de los archivos compilados, verificar los pull request de los colaboradores o la verificación de que todos los unit test estén ejecutándose correctamente

Con respecto a calidad de software se podría configurar un Sonar Qube para el proyecto donde los reportes se crearían para cada Feature Module
