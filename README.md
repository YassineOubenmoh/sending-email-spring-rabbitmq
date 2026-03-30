<h1>sending-email-spring-rabbitmq</h1>

<p>
This project demonstrates how to build a Spring Boot application that enables users to follow one another.
When a follow request is initiated, the recipient is notified via email.
Upon acceptance of the request, a confirmation email is sent to the original sender.
</p>

<h2>Setup (Setup Steps)</h2>

<h3>1. Run RabbitMQ container</h3>
<pre>
docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
</pre>

<p>
RabbitMQ Management UI: <a href="http://localhost:15672">http://localhost:15672</a><br/>
Username: guest <br/>
Password: guest
</p>

<h3>2. Run MailHog container</h3>
<pre>
docker run -d --name mailhog -p 1025:1025 -p 8025:8025 mailhog/mailhog
</pre>

<p>
MailHog Web UI: <a href="http://localhost:8025">http://localhost:8025</a>
</p>

<h2>Overview</h2>
<ul>
  <li>RabbitMQ is used as a message broker to handle email tasks asynchronously.</li>
  <li>MailHog is used to capture and view emails locally for testing.</li>
  <li>Spring Boot handles business logic and email processing.</li>
</ul>
