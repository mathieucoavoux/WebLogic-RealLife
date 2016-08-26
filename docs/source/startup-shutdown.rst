Startup WebLogic
----------------

Admin server
~~~~~~~~~~~~~~~

Managed server
~~~~~~~~~~~~~~~

Depending on your configuration, managed servers can take time to startup. 
WebLogic deploys its application after two phases.

- Phase 1: The application has the "prepare" status. At this step WebLogic controls package integrity and ensure the application can be deployed. We can meet errors at this step if a package has been corrupted while transferring from the Admin server to managed servers.
- Phase 2: The application has the "active" status. WebLogic deploys the application in all managed servers of the cluster.

Real life:
On a WebLogic 12c platform we corrupted our WAR application in different ways. You can find below results of theses tests

+----------------------------------------+-------------------+-----------------------+
| Test description                       | Deployment status | Application available |
+========================================+===================+=======================+
| Delete plan.xml                        | Active            | Yes                   |
| Delete web.xml                         | Active            | Yes                   |
| Delete weblogic.xml                    | Active            | Yes                   |
| Corrupt war file with dd command       | Active            | No                    |
+----------------------------------------+-------------------+-----------------------+

Startup and Shutdown Classes
""""""""""""""""""""""""""""

StartLogger example
```````````````````

In this example we developed a class named DatabaseLogger which writes the current date into t_start and a class named StartServlet which calss DatabaseLogger.
When configuring a startup class in WebLogic console, the managed server will try to call the method main into this class.
If we do not write this method the following error will occur during startup:
.. code-block:: java
java.lang.NoSuchMethodException: app.StartServlet does not define 'public static void main(String[])'


