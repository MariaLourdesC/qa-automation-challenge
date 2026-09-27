#!/usr/bin/env python3
"""Resumen de resultados JMeter CSV JTL: python3 analizar_jtl.py results/login.jtl"""
import csv,sys,statistics
from datetime import datetime
if len(sys.argv)!=2:sys.exit('Uso: python3 analizar_jtl.py archivo.jtl')
with open(sys.argv[1],newline='',encoding='utf-8-sig') as f: rows=list(csv.DictReader(f))
if not rows:sys.exit('JTL sin muestras')
ms=[float(r['elapsed']) for r in rows];ts=[int(r['timeStamp']) for r in rows];errors=sum(r.get('success','').lower()!='true' for r in rows)
duration=max((max(ts)-min(ts))/1000,1);p95=sorted(ms)[int((len(ms)-1)*.95)]
print(f'Muestras: {len(rows)} | Duración entre inicios: {duration:.1f}s | TPS aprox.: {len(rows)/duration:.2f}')
print(f'Errores JMeter: {errors} ({100*errors/len(rows):.2f}%) | Promedio: {statistics.mean(ms):.1f} ms | p95: {p95:.1f} ms | Máximo: {max(ms):.1f} ms')
print(f'Respuestas > 1500 ms: {sum(x>1500 for x in ms)} ({100*sum(x>1500 for x in ms)/len(ms):.2f}%)')
print('Criterio TPS >=20:',len(rows)/duration>=20,'| Errores <3%:',100*errors/len(rows)<3,'| Todas las respuestas <=1500ms:',max(ms)<=1500)
print('Nota: TPS calculado entre inicios de muestras; revisar gráfico por intervalo para detectar caídas.')
