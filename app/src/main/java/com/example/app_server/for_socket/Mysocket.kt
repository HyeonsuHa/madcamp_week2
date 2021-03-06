package com.example.app_server.for_socket

import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException


class SocketApplication {
    companion object {
        private lateinit var socket : Socket
        fun get(): Socket {
            try {
                socket = IO.socket("http://192.249.18.122:80")
            } catch (e: URISyntaxException) {
                e.printStackTrace()
            }
            return socket
        }
    }
}