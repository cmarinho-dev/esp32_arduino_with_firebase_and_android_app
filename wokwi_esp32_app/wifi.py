def conectar(ssid, senha):
    import network
    wlan = network.WLAN(network.STA_IF)
    wlan.active(True)
    wlan.connect(ssid, senha)
    
    while not wlan.isconnected():
        pass

    print("SUCESSO - Conectar ao Wifi ")
