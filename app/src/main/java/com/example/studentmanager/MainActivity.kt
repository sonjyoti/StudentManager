
package com.example.studentmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentmanager.ui.theme.StudentManagerTheme

data class Student(
    val id: Int,
    val name: String,
    val age: Int,
    val course: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudentManagerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    StudentManagerScreen()
                }
            }
        }
    }
}

@Composable
fun StudentManagerScreen() {
    var students by remember {
        mutableStateOf(
            listOf(
                Student(1, "John", 18, "Computer Science"),
                Student(2, "Alice", 20, "Mathematics"),
                Student(3, "Rahul", 19, "Information Technology"),
                Student(4, "Priya", 21, "Physics")
            )
        )
    }

    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var course by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }
    var ageError by remember { mutableStateOf(false) }
    var courseError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Text(
            text = "Student Manager",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text("Manage your student records")

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Add New Student",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
                nameError = false
            },
            label = { Text("Student Name") },
            isError = nameError,
            supportingText = {
                if (nameError) {
                    Text("Name cannot be empty")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
                ageError = false
            },
            label = { Text("Age") },
            isError = ageError,
            supportingText = {
                if (ageError) {
                    Text("Enter a valid positive age")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = course,
            onValueChange = {
                course = it
                courseError = false
            },
            label = { Text("Course") },
            isError = courseError,
            supportingText = {
                if (courseError) {
                    Text("Course cannot be empty")
                }
            },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                val parsedAge = age.toIntOrNull()

                nameError = name.isBlank()
                ageError = parsedAge == null || parsedAge <= 0
                courseError = course.isBlank()

                if (!nameError && !ageError && !courseError) {
                    val nextId = (students.maxOfOrNull { it.id } ?: 0) + 1

                    val newStudent = Student(
                        id = nextId,
                        name = name.trim(),
                        age = parsedAge!!,
                        course = course.trim()
                    )

                    students = students + newStudent

                    name = ""
                    age = ""
                    course = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Add Student")
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Students (${students.size})",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))


        students.forEach { student ->
            StudentCard(
                student = student,
                onDelete = {
                    students = students.filter {
                        it.id != student.id
                    }
                }
            )

            Spacer(modifier = Modifier.height(12.dp))
        }

    }
}

@Composable
fun StudentCard(
    student: Student,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = student.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Age: ${student.age}",
                    modifier = Modifier.weight(1f)
                )

                Text(text = "ID: ${student.id}")
            }

            Spacer(modifier = Modifier.height(4.dp))

            Text(text = student.course)

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onDelete
            ) {
                Text("Delete Student")
            }
        }
    }
}
