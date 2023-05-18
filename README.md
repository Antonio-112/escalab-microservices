<h1>Bienvenido al Repositorio de Microservicios Java</h1>
<p>Este repositorio alberga una serie de microservicios diseñados para funcionar juntos como una aplicación integral de servidor web. Cada microservicio se ha creado con un propósito específico, y todos juntos constituyen un sistema completo y coherente.</p>
<h2>Conjunto de Microservicios</h2>
<table>
	<thead>
		<tr>
			<th>Nombre del Servicio</th>
			<th>Descripción</th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td>microconfig</td>
			<td>Responsable de gestionar las configuraciones del sistema, permitiendo una separación de responsabilidades y una mayor flexibilidad en todo el sistema.</td>
		</tr>
		<tr>
			<td>microeureka</td>
			<td>Agente de descubrimiento de servicios que permite a los servicios encontrar y comunicarse entre sí sin problemas, eliminando la necesidad de direcciones IP y puertos codificados.</td>
		</tr>
		<tr>
			<td>microproducto</td>
			<td>Este servicio centrado en el negocio se encarga de la gestión de productos y su disponibilidad, garantizando un alto nivel de cohesión y bajo acoplamiento entre los servicios.</td>
		</tr>
		<tr>
			<td>microzuul</td>
			<td>Actúa como una puerta de enlace que enruta las solicitudes de los clientes al microservicio apropiado, ofreciendo funciones como autenticación, limitación de tasas y respuestas de respaldo.</td>
		</tr>
		<tr>
			<td>security</td>
			<td>Proporciona seguridad mediante la autenticación y autorización de usuarios, garantizando diferentes niveles de acceso para diferentes usuarios y asegurando las acciones de los usuarios.</td>
		</tr>
	</tbody>
</table>
<h2>Tecnologías Implementadas</h2>
<ul>
	<li>Java 8</li>
	<li>Spring Boot</li>
	<li>Netflix Eureka</li>
	<li>Spring Cloud Config</li>
	<li>Netflix Zuul</li>
	<li>Spring Security</li>
</ul>
<h2>Cómo Ejecutar el Proyecto</h2>
<ol>
	<li>Haz un fork o clona el repositorio en tu máquina local</li>
	<li>Abre cada proyecto de microservicio en tu IDE preferido</li>
	<li>Ejecuta cada microservicio individualmente</li>
	<li>Sigue las instrucciones en la consola para interactuar con la aplicación</li>
</ol>
<p>Asegúrate de tener todas las tecnologías necesarias instaladas y configuradas correctamente antes de ejecutar la aplicación. Para obtener más información, consulta la documentación respectiva de cada tecnología mencionada anteriormente. ¡Feliz programación!</p>
