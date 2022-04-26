import { Injectable } from '@angular/core';

declare var require: any

const SockJs = require('sockjs-client');
const Stomp = require('stompjs');

@Injectable()
export class WebSocketService {

  public connect() {
    const socket = new SockJs('http://localhost/socket');

    return Stomp.over(socket);
  }
}
