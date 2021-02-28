package app.erickalvz.shared.rx

import io.reactivex.Scheduler

interface SchedulerProvider {
    val mainThread: Scheduler
    val background: Scheduler
    val computation: Scheduler
}