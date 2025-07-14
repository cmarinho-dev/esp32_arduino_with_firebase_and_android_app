# 🚗 Controle de Vagas em Estacionamentos com ESP32, Firebase e App Android

Este projeto tem como objetivo o **monitoramento em tempo real de vagas disponíveis em estacionamentos**, utilizando sensores ultrassônicos, um display OLED e o ESP32, com integração ao **Firebase Realtime Database** e um **aplicativo Android** para configuração e visualização.

---

## 📷 Visão Geral

<div align="center">
  <img src="https://user-images.githubusercontent.com/..." alt="Demonstração do Projeto" width="600"/>
</div>

---

## 🧠 Como Funciona

- Dois sensores ultrassônicos **HC-SR04** são posicionados nas portas de **entrada** e **saída** do estacionamento.
- Os sensores detectam veículos passando a uma distância menor que 4 metros (400 cm), simulando a entrada ou saída de um carro.
- Os dados são processados pelo **ESP32**, atualizando:
  - Um **display OLED** que mostra as vagas em tempo real.
  - O **Firebase Realtime Database**, onde os dados ficam disponíveis online.
- Um **aplicativo Android** permite:
  - Definir o número máximo de vagas e as vagas inicialmente disponíveis.
  - Visualizar as vagas em tempo real diretamente do Firebase.

---

## 🛠️ Tecnologias e Componentes

### 🔌 Hardware
- ESP32
- 2x sensores ultrassônicos HC-SR04
- Display OLED I2C (128x64)
- Protoboard e jumpers

### 💻 Software
- [MicroPython](https://micropython.org/)
- Firebase Realtime Database
- App Android (Jetpack Compose + Firebase SDK)

---

## 📱 Funcionalidades do App

- **Tela de configuração inicial**:
  - Defina o número total de vagas (`vagas_max`)
  - Defina as vagas disponíveis no momento (`vagas_disponiveis`)
- **Tela de monitoramento**:
  - Exibe os dados em tempo real do Firebase
  - Os valores são atualizados automaticamente conforme o ESP32 envia os dados

---

## 🔗 Firebase

Estrutura usada no Realtime Database:

```json
{
  "vagas_disponiveis": 20,
  "vagas_max": 90
}
