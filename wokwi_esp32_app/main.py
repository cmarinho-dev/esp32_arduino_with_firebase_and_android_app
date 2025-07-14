from machine import Pin, I2C
import time, ssd1306, hcsr04, wifi, urequests

def imprimir_mensagem_no_display():
    mensagem = str(vagas_disponiveis) + " / " + str(vagas_max)
    oled.fill(0)
    oled.text("Vagas Disponiveis", 10, 10)
    oled.text(mensagem, 10, 30)      
    oled.show()

def enviar_para_firebase():
    try:
        data = {
            "vagas_disponiveis": vagas_disponiveis,
            "vagas_max": vagas_max
            }
        response = urequests.put(firbase_url, json=data)
        response.close()
    except Exception as e:
        print("Erro ao enviar: ", e)

def ler_do_firebase(url):
    try:
        resposta = urequests.get(url)
        valor = resposta.json()
        resposta.close()
        print("SUCESSO - Recuperar Valor ",valor)
        return int(valor)
    except Exception as e:
        print("Erro ao ler Firebase:", e)
        return None


i2c = I2C(0, scl=Pin(22), sda=Pin(21))
oled = ssd1306.SSD1306_I2C(128, 64, i2c)
sensor_entrada = hcsr04.HCSR04(trigger_pin=12, echo_pin=14, echo_timeout_us=1000000)
sensor_saida = hcsr04.HCSR04(trigger_pin=4, echo_pin=0, echo_timeout_us=1000000)
wifi.conectar("Wokwi-GUEST","")

firbase_url = "https://esp32-estacionamento-default-rtdb.firebaseio.com/.json"

vagas_max = ler_do_firebase("https://esp32-estacionamento-default-rtdb.firebaseio.com/vagas_max.json")
vagas_disponiveis = ler_do_firebase("https://esp32-estacionamento-default-rtdb.firebaseio.com/vagas_disponiveis.json")
imprimir_mensagem_no_display()

while True:
    distancia_entrada = int(sensor_entrada.distance_cm())
    distancia_saida = int(sensor_saida.distance_cm())
    if distancia_entrada < 400:
        vagas_disponiveis -= 1
        imprimir_mensagem_no_display()
        enviar_para_firebase()
        time.sleep(3)
        continue
    if distancia_saida < 400:
        vagas_disponiveis += 1
        imprimir_mensagem_no_display()
        enviar_para_firebase()
        time.sleep(3)
        continue
    time.sleep(1)
    