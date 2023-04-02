package pt.isel.ls.tasks.services

import pt.isel.ls.tasks.project.InstanceProjectTest
import kotlin.test.BeforeTest

abstract class ClearData {
    @BeforeTest
    fun rebootDataMem() {
        InstanceProjectTest.db.reset()
    }
}