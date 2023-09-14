package com.github.hugovallada.sseevent.emitter

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.lang.Thread.sleep
import java.util.concurrent.Executors

@Controller
@RequestMapping("/emitter")
@CrossOrigin(origins = ["*"])
class EventEmitter {


    @GetMapping
    fun emit(): SseEmitter {
        println("Conexão SSE INICIADA")
        val emitter = SseEmitter(-1)
        val executor = Executors.newSingleThreadExecutor()

        executor.execute {
            try {
//                repeat(10) {
//                    sleep(3000)
//                    emitter.send("Ola $it")
//                }
                Thread.sleep(15000)
                emitter.send("Ola Mundo!")
            } catch (ex: Exception) {
                emitter.completeWithError(ex)
            } finally {
                emitter.complete().also { println("Conexão SSE Finalizada") }
            }
        }

        executor.shutdown()
        return emitter
    }

}