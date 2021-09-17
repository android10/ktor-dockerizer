package com.fernandocejas.ktor.core.docker

import com.fernandocejas.ktor.DockerizerExtension
import com.fernandocejas.ktor.DockerizerPlugin
import com.fernandocejas.ktor.UnitTest
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.slot
import io.mockk.verify
import org.amshove.kluent.shouldBe
import org.gradle.api.Project
import org.gradle.api.tasks.Exec
import org.gradle.api.tasks.TaskContainer
import org.junit.Before
import org.junit.Test

class TaskTest: UnitTest() {

    private lateinit var task: MyTask
    private lateinit var command: Task.Command

    @MockK private lateinit var project: Project
    @MockK private lateinit var extension: DockerizerExtension
    @MockK private lateinit var taskContainer: TaskContainer

    @Before
    fun setUp() {
        every { project.tasks } returns taskContainer

        command = Task.Command.fromString(TASK_COMMAND)
        task = MyTask(TASK_NAME, TASK_DESCRIPTION, command, project, extension)
    }

    @Test
    fun `given a Dockerizer Task, when register is called, then it is registered via gradle`() {
        val slotExec = slot<Exec>()
        every { taskContainer.register(TASK_NAME, Exec::class.java, capture(slotExec)) }

//        taskContainer.register(TASK_NAME, Exec::class.java, capture(slotExec))
        task.register()

        verify(exactly = 1) { taskContainer.register(TASK_NAME, Exec::class.java, withArg<Exec> {
            it.description shouldBe TASK_DESCRIPTION
        }) }

        slotExec.captured.group shouldBe DockerizerPlugin.TASK_GROUP
//        verify(exactly = 1) { taskContainer.register(TASK_NAME, Exec::class.java, any()) }
//        verify(exactly = 1) { taskContainer.register(eq(TASK_NAME), eq(Exec::class.java)) }
    }

    companion object {
        private const val TASK_NAME = "myTask"
        private const val TASK_DESCRIPTION = "myDescription"
        private const val TASK_COMMAND = "pwd"
    }

    private class MyTask(override val name: String,
                         override val description: String,
                         override val command: Command,
                         project: Project,
                         extension: DockerizerExtension) : Task(project, extension)
}
