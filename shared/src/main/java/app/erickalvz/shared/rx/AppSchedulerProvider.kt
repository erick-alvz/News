package app.erickalvz.shared.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider(
    override val mainThread: Scheduler = AndroidSchedulers.mainThread(),
    override val background: Scheduler = Schedulers.io(),
    override val computation: Scheduler = Schedulers.computation()
) : SchedulerProvider