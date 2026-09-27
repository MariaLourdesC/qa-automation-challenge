PRUEBA TÉCNICA - PERFORMANCE CON JMETER

Contenido:
- login_20tps.jmx: plan de carga POST https://fakestoreapi.com/auth/login.
- usuarios.csv: datos de entrada del enunciado (columnas user,passwd).
- InformeResultados.docx: análisis del ejercicio 2, basado únicamente en el resumen y gráfico facilitados.
- conclusiones.txt: alcance, hallazgos y limitaciones.
- analizar_jtl.py: análisis del JTL de una ejecución real.

REQUISITOS
- Apache JMeter 5.6.3 (versión objetivo del archivo JMX)
- Java 17
- Python 3 para analizar_jtl.py
Comprobar versiones instaladas: jmeter --version ; java -version ; python3 --version.

SERVICIO Y DATOS
- Método: POST
- URL: https://fakestoreapi.com/auth/login
- Header: Content-Type: application/json
- Body: {"username":"${user}","password":"${passwd}"}
- CSV: usuarios.csv, encabezado user,passwd; JMeter reutiliza los registros.
- El CURL original especifica --max-time 60; se configuró un timeout de respuesta de 60000 ms y conexión de 5000 ms.
- IMPORTANTE: las credenciales del enunciado NO están verificadas como cuentas válidas de FakeStore API. Confirmar previamente con una solicitud aislada si el servicio acepta esas credenciales. Un 401 por credenciales inválidas no demuestra una falla de rendimiento.

CONFIGURACIÓN DE CARGA
50 usuarios virtuales, ramp-up 30 s, duración 180 s y temporizador con objetivo 20 TPS (1200 muestras/minuto). Un temporizador no garantiza el throughput: comprobar TPS real en el JTL y reporte. La Duration Assertion marca respuestas superiores a 1500 ms como fallidas. La Response Assertion espera HTTP 200 por defecto; si las credenciales son inválidas, NO cambiar a 401 para presentar el ejercicio como una prueba de login exitosa. Conseguir credenciales válidas o documentar la limitación.

EJECUCIÓN
Desde la carpeta performance-tests/:
  mkdir -p results
  jmeter -n -t login_20tps.jmx -l results/login.jtl -e -o report-html
Opcional: -JTHREADS=50 -JRAMP_UP=30 -JDURATION=180 -JTPS=20
Para una nueva ejecución, eliminar o renombrar los resultados anteriores y report-html; JMeter requiere una carpeta de reporte nueva o vacía.

REVISIÓN
  python3 analizar_jtl.py results/login.jtl
  Abrir report-html/index.html
Criterios: throughput real >= 20 TPS, tasa de error < 3 % y tiempo de respuesta <= 1500 ms. Indicar si el umbral de tiempo se evalúa para todas las solicitudes o un percentil: el enunciado no especifica percentil.

GITHUB
Copiar esta carpeta como performance-tests/ al repositorio qa-automation-challenge:
  git add performance-tests/
  git commit -m "Add JMeter login load test and results analysis"
  git push origin main
No subir datos reales de acceso ni afirmar resultados del ejercicio 1 sin ejecutar el JMX.
