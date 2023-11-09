## 1. 동작 파라미터화란?

> 파라미터 : 변수의 특별한 한 종류로서, 함수 등과 같은 서브루틴의 인풋으로 제공되는 여러 데이터 중 하나를 가리키기 위해 사용된다 <br>

동작 파라미터화 : 동작 자체를 메서드에 인자로 전달하기 위한 방법

## 2. 동작 파라미터화의 필요성 

우리는 다음과 같은 `Student` 클래스를 사용

```java
class Student{
    int height;  
    String name;  
  
    //AllArgumentConstructor
}
```

학생을 정렬시키는 방법 : 
1. 학급 번호를 정하기 위해 학생들을 이름 순으로 정렬
2. 체육대회에서 키를 순서로 정렬
3. ...

### 1. 학생을 정렬 시키는 방법마다 메서드로 구현

```java
public class Sort {
    public static void main(String[] args) {
        List<Student> students = getStudentList();
    
        List<Student> sortedByName = sortStudentByName(students);
    
        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
    
    private static List<Student> sortStudentByName(List<Student> students) {
        List<Student> sortedList = new ArrayList<>();
    
        //삽입 정렬
        for (Student student : students) {
            int index = 0;
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                if(innerStudent.getName().compareTo(student.getName()) > 0)
                    break;
            }
            sortedList.add(index, student);
        }
    
        return sortedList;
    }
  
}
```

-> 각각의 상황마다 사용할 메서드를 따로 분리해 둔다.
- 이름 순으로 정렬 : `sortStudentByName`
- 키 순으로 정렬 : `sortStudentByHeight`

**문제점**
1. 상황이 늘어날 때 마다 메서드가 하나씩 증가한다.

학생마다 학급 번호가 주어졌다고 가정하면, 이 학급 번호로 정렬을 할 때 사용할 메서드를 새로 만들어야 한다.
-> 유지보수가 힘들어 진다.

2. 메서드의 구현 변경이 필요할 때 유지보수가 힘들어진다.

지금의 메서드들은 모두 동일한 동작의 흐름을 가지고 있다.
    1. 새 리스트 생성
    2. 기존 리스트의 값들을 정렬시켜 저장
    3. 정렬된 리스트 반환

이 때, 전체 흐름 중 일부분이 변경된다면??
즉, 메서드에서 사용한 삽입정렬 대신, Quick sort를 사용하게 된다면 많은 메서드들을 전부 변경해주어야 함
코드의 흐름이 중복되는 것 역시 코드의 중복에 해당하며 유지보수가 힘들어 지는 문제로 이어진다.

3. 코드의 길이가 길어져 필요한 메서드를 찾아가기 힘들어진다.

각각의 메서드를 모두 하나의 클래스에 구현하게 되면 필요한 부분을 찾아가기 힘들어지며, 가독성도 떨어지게 된다.

### 2. 추상 클래스를 사용하기

추상 클래스를 만들어 변경이 필요한 부분만 따로 구현한다면 어떨까?

아래는 학생을 정렬할 기준을 가져오는 메서드를 추상 메서드로 정의한 추상 클래스이다.

```java
public abstract class SortStudent <K extends Comparable<K>>{
    
    public abstract K getCriteria(Student student);
    
    public List<Student> sortStudent(List<Student> students){
        List<Student> sortedList = new ArrayList<>();

        for (Student student : students) {
            int index = 0;
            for (; index < sortedList.size(); index++) {
                Student innerStudent = sortedList.get(index);
                if(getCriteria(innerStudent).compareTo(getCriteria(student)) > 0)
                    break;
            }
            sortedList.add(index, student);
        }

        return sortedList;
    }
}
```

이제 코드의 사용이 조금 더 간결해 졌다!
```java
class StudentSortV2{
    
    public static void main(String[] args) {
        List<Student> students = getStudentList();
        SortStudentByName sortByName = new SortStudentByName();

        List<Student> sortedByName = sortByName.sortStudent(students);

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }

    static class SortStudentByName extends SortStudent<String>{
        @Override
        public String getCriteria(Student student) {
            return student.getName();
        }
    }
}
```
보는 바와 같이, 어떤 값을 기준으로 정렬할 것인지에 대한 부분만 구현을 한 `SortStudent`의 자식 클래스를 사용해 `main` 함수가 훨씬 간결해 졌다.
하지만, 여전히 문제점은 남아있다.

**문제점**
1. 요구사항이 증가함에 따라 클래스의 수가 증가한다.

이번에는 메서드의 수 대신, 클래스의 수가 증가한다.
예를 들어, 3가지의 다른 기준으로 학생을 정렬해야 한다면, 우리는 3 가지 다른 정렬 클래스가 필요해 진다.

2. 내부 구현을 변경할 수 없다.

이번의 방법 역시 내부의 정렬 방법을 변경해야 하는 요구사항 변경에 대처를 하기가 어렵다.

## 정리

이번 시간에는 간단하게 동작 파라미터화가 필요한 이유에 대해 알아보았다.
동작을 파라미터화 할 수 있다면, 우리가 위에서 겪었던 문제상황을 해결할 수 있을 것이다.
즉, "정렬 기준을 추출하는 기능", "학생을 정렬하는 방법" 등을 파라미터화 하여 전달할 수 있다면, 우리가 가진 문제들이 해결될 것이다.