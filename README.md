# Knowledge Testing Console Application

## Description

This is a console application developed for the largest programming training center in Ukraine. The application aims to solve the business problem of testing the knowledge of graduates to create a ranking and identify the best candidates for employment.

## Installation

The application is a runnable JAR file. The path to an encrypted file, which contains both input and output data, is passed to the program.

## Usage

### Input Data
The input data is a Java test with a list of 10 questions with correct answers and time for each question.

### Output Data
The output data includes the first and last name of the test taker, the date and time when the test was taken, the result in percentage, and all the answers.

### Roles
The application provides for the role of a student and a mentor. Authentication and data encryption are mandatory requirements.

## Development

The console application is a minimum viable product (MVP) that demonstrates basic skills in development, design, and coding in Java. The application uses design patterns, such as the Decorator pattern. The choice of technology is not a concern at this stage.

In the future, we plan to develop a full-fledged web application based on this prototype.

## Note
For demonstration purposes, the number of test attempts is set to 1. The mentor has the ability to view not only the student's result but also all their answers.

# Application Usage Guide

## Description
This application is designed to conduct tests with a time limit for each question, calculate scores using a binary approach, read questions from a file, write results to a file, display student results, authenticate users, encrypt/decrypt data, and allow mentors to view results.

## Usage
1. Create instances of `Student`, `Test`, `Question`, `Mentor`, and `AuthenticationService`.
2. Fill in the information about the student, test, and questions.
3. Create an instance of `ConsoleResultViewer`, `FileResultReader`, `BinaryScoreCalculator`, `Test`, or `EncryptionService`.
4. For `ConsoleResultViewer`, call the `view(student, test)` method to display test results.
5. For `FileResultReader`, call the `read(filePath)` method to read results from a file.
6. For `BinaryScoreCalculator`, call the `calculate(answers, questions)` method to calculate scores.
7. For `Test`, call the `conductTest(student)` method to conduct a test.
8. For `AuthenticationService`, call the `authenticate(username, password)` method to authenticate a user.
9. For `EncryptionService`, call the `encrypt(data)` and `decrypt(encryptedData)` methods to encrypt and decrypt data, respectively.
10. For `FileIOService`, call the `readQuestionsFromFile(filePath)` and `writeResultsToFile(filePath, student, test)` methods to read questions from a file and write results to a file, respectively.
11. For `Mentor`, call the `viewResults(student, test)` and `getResultFromFile(filePath)` methods to view results and read results from a file, respectively.
12. For `Student`, call the `saveAnswers(answers)` and `calculateScore(questions)` methods to save answers and calculate scores, respectively.
13. For `PasswordHash`, call the `generateStrongPasswordHash(password)` and `validatePassword(enteredPassword, storedHash)` methods to generate a password hash and check a password, respectively.

## Example Code
```java
Student student = new Student("First Name", "Last Name", "Student Answers");
List<String> options = Arrays.asList("Option 1", "Option 2", "Option 3");
Question question = new Question("Question Text", options, "Correct Answer");
List<Question> questions = Arrays.asList(question);
Test test = new Test(questions);
ConsoleResultViewer viewer = new ConsoleResultViewer();
FileResultReader reader = new FileResultReader();
BinaryScoreCalculator calculator = new BinaryScoreCalculator();
Test testConductor = new Test("Test Questions", "Time per Question", "User Input Reader");
AuthenticationService authService = new AuthenticationService();
EncryptionService encryptionService = new EncryptionService();
FileIOService fileIOService = new FileIOService();
Mentor mentor = new Mentor();
PasswordHash passwordHash = new PasswordHash();

viewer.view(student, test);
reader.read("file path");
double score = calculator.calculate(student.getAnswers(), test.getQuestions());
testConductor.conductTest(student);
User user = authService.authenticate("username", "password");
String encryptedData = encryptionService.encrypt("data");
String decryptedData = encryptionService.decrypt(encryptedData);
List<Question> questionsFromFile = fileIOService.readQuestionsFromFile("file path for questions");
fileIOService.writeResultsToFile("file path for results", student, test);
mentor.viewResults(student, test);
mentor.getResultFromFile("file path for results");
```
## Result
The result will be displayed in the console in the following format:
```
Result student: First Name Last Name
Score: X%
Question 1: Question Text
Student answer: Student Answer
Right answer: Correct Answer
```

## Support
If you encounter any problems or have any questions about this application, please feel free to contact us. You can:

- Join our community chat room to discuss with other users and developers.
- Send us an email at vadym.piskun.dp@gmail.com.

We appreciate your feedback and will do our best to assist you!

## Roadmap
We have many ideas for future releases and are excited to improve this application based on user feedback. Here are some features we're considering:

- Feature 1: A brief description of the feature.
- Feature 2: ***
- Feature 3: ***

Please note that these are just ideas and are subject to change. We welcome your suggestions and feedback on these potential features!

## Contributing
State if you are open to contributions and what your requirements are for accepting them.

For people who want to make changes to your project, it's helpful to have some documentation on how to get started. Perhaps there is a script that they should run or some environment variables that they need to set. Make these steps explicit. These instructions could also be useful to your future self.

You can also document commands to lint the code or run tests. These steps help to ensure high code quality and reduce the likelihood that the changes inadvertently break something. Having instructions for running tests is especially helpful if it requires external setup, such as starting a Selenium server for testing in a browser.

## Authors and acknowledgment
Vadym Piskun - telegram:@vdm_pskn

## License
For open source projects, say how it is licensed.

## Project Status

**NOTE:** This project is currently in the development phase. However, due to time constraints, the pace of development may slow down or stop completely. If you're interested in this project and would like to help it continue, feel free to fork it or volunteer to become a maintainer or owner. We appreciate your support and contribution!

If you're interested in becoming a maintainer, please contact us at [@vdm_pskn].
