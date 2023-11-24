import data.Priority
import data.Task
import data.TasksRepository
import data.TasksRepositoryMemory
import io.github.serpro69.kfaker.Faker
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class MainTest {
    private lateinit var testRepository: TasksRepository
    private val faker = Faker()

    @BeforeTest
    fun populateTasks() {
        val tasksRepositoryMemory = TasksRepositoryMemory()
        val iterations = faker.random.nextInt(2,5)
        for (i in 1..iterations) {
            val name = faker.cowboyBebop.character()
            val priority = Priority.values().random()
            tasksRepositoryMemory.addTask(Task(name = name, priority = priority))
        }
        testRepository = tasksRepositoryMemory
    }

    @Test
    fun addTaskAndCheckAppearanceTest() {
        val name = faker.cowboyBebop.character()
        val priority = Priority.values().random()
        val newTaskId = testRepository.addTask(Task(name = name, priority = priority))
        val lastIdFromTaskList = testRepository.getTasks().last().id
        assertEquals(newTaskId,lastIdFromTaskList)
    }

    @Test
    fun checkFinishFilterAfterFinishTaskTest() {
        val name = faker.cowboyBebop.character()
        val priority = Priority.values().random()
        val newTaskId = testRepository.addTask(Task(name = name, priority = priority))
        testRepository.completeTask(newTaskId)
        val listOfCompletedTasks = testRepository.getTasks(completed = true).size
        assertEquals(1,listOfCompletedTasks,"Expected 1 task in filter, but got $listOfCompletedTasks")
    }

    @Test
    fun checkSortingByNameTest() {
        // Sorting is not implemented yet
    }

    @Test
    fun checkSortingByDueTimeTest() {
        // Sorting is not implemented yet
    }
}