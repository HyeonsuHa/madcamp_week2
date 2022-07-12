package com.example.app_server

import com.example.app_server.for_socket.SocketApplication
import io.socket.client.Socket

class Mysocket {
    companion object {
        val mysocket:Socket = SocketApplication.get()
    }
}