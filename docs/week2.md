## 1. Review

저번 주에는 동작 파라미터화가 필요한 이유에 대해 알아 보았다.
우리의 목표는 "메서드가 자신이 수행해야 할 행위 자체를 전달 받는 것" 이다!

## 2. 동작 파라미터화 

### 1. 인터페이스를 사용하는 방법

이전에서의 추상 클래스 대신, 이번에는 인터페이스를 사용해 보자.

우리가 만들 인터페이스는 각각 객체에서 비교할 키를 추출할 `KeyExtractor`와 전달받은 값을 비교할 `Comparator`이다. 
각각의 인터페이스는 단 하나만의 메서드를 가지고 있음을 기억하자!

```java
public interface Comparator <Key>{
    int compareKey(Key key1, Key key2);
}

public interface KeyExtractor<Model, Key>{
    Key getKey(Model model);
}
```

그리고, 두 인터페이스를 전달 받아 학생들을 정렬할 `SortStudent`를 만들어 준다.

이제 우리의 예제는 다음과 같이 사용할 수 있다.

```java
public class StudentSortV3{
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        List<Student> sortedByName =
                SortStudent.sort(students, new KeyExtractor<Student, String>() {
                    @Override
                    public String getKey(Student student) {
                        return student.getName();
                    }
                }, new Comparator<String>() {
                    @Override
                    public int compareKey(String key1, String key2) {
                        return key1.compareTo(key2);
                    }
                });

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
```

그런데, 이렇게 보니 추상 클래스와 뭐가 다른지 잘 모르겠다.
추상클래스 역시 다음과 같이 익명 클래스로 사용할 수 있지 않은가!

```java
List<Student> sortByHeight = new SortStudent<Integer>() {
    @Override
    public Integer getCriteria(Student student) {
        return student.getHeight();
    }
}.sortStudent(students);
```

그런데, 인터페이스를 사용한 코드를 잘 보니 IDE가 `Anonymous new KeyExtractor<Student, String>() can be replaced with lambda ` 
와 같은 가이드를 제공한다.
익명의 `KeyExtractor`는 람다 식으로 대체할 수 있다는 말이다.

람다식으로 대체한 결과는 다음과 같다.
```java
public class StudentSortV3{
    public static void main(String[] args) {
        List<Student> students = Student.getStudents();
        List<Student> sortedByName =
                SortStudent.sort(students, student -> student.getName(), (key1, key2) -> key1.compareTo(key2));

        for (Student student : sortedByName) {
            System.out.println(student.getName());
        }
    }
}
```

코드가 비교도 안될 만큼 짧아졌다!!

### 람다 식이란?

그렇다면 람다 식은 무엇일까? 그리고, 왜 위와 같이 익명의 인터페이스들이 람다 식으로 대체될 수 있었던 걸까?

람다 식은 메서드를 표현하는 다른 방법이다.
기본적인 구성은 다음과 같다.
```java
() // 파라미터 선언
->
{} // 함수의 몸체
```

예시 : 
```java
// #1
void printString(String s){
    System.out.println(s);
}
//=>
(String s) -> {System.out.println(s)}
        
// #2
int add(int a, int b){
    return a + b;
}
//=>
(int a, int b) -> a+b; 
//함수의 몸체에 return 문만 존재할 경우, {}를 생략할 수 있다.
```

메서드를 간단하게 식으로 표현할 수 있다면, 우리가 인터페이스로 구현해야 할 부분도 간단하게 식으로 전달할 수 있다는 말이다!
그리고, 위와 같이 **단 하나만의 추상 메서드를 가지는 인터페이스**는 우리가 본 것 처럼 람다식으로 대체 가능하다.

### 함수형 인터페이스

방금 살펴본 것 처럼 **단 하나만의 추상 메서드를 가지는 인터페이스**는 람다 식으로 대체할 수 있었다.
그리고 자바에서는 이를 함수형 인터페이스라 한다.

우리가 앞서 만들었던 `Comparator`, `KeyExtractor` 역시 함수형 인터페이스에 해당한다.

그리고, 사실 자바에서는 다양한 종류의 기본 함수형 인터페이스들을 제공한다.
자주 사용되는 함수형 인터페이스의 종류는 아래와 같다.

`Consumer<T>` - `<T>` 타입의 객체를 인자로 전달받아 해당 객체를 "소비" 한다.
`(T t) -> {}`
```java
@FunctionalInterface
public interface Consumer<T> {
    void accept(T t);
}
```

`Supplier<T>` - `<T>` 타입의 객체를 생성하여 리턴한다.
`() -> T t`
```java
@FunctionalInterface
public interface Supplier<T> {
    T get();
}
```

`Function<T, R>` - `<T>` 타입 객체를 받아 `<R>` 타입 객체를 리턴한다.
`(T t) -> R r;`
```java
@FunctionalInterface
public interface Function<T, R> {
    R apply(T t);
}
```

이외에도, 우리가 정렬을 하며 자주 사용했을 `Comparator` 역시 함수형 인터페이스 이다.
`(T o1, T o2) -> int result;`
```java
@FunctionalInterface
public interface Comparator<T> {
    int compare(T o1, T o2);
}
```

이와 같이 함수형 인터페이스와 람다를 사용하면 간단하게 동작을 메서드의 인자로 넘겨줄 수 있게 되는 것이다!!

## 참고

이번 발표에서 다루진 않았지만, 람다 표현식 대신 이미 구현되어 있는 클래스의 메서드를 함수형 인터페이스 인자로 전달할 수 도 있다.
`::`를 사용하는 메서드 참조가 이 것이며, V5는 메서드 참조를 사용한 코드이니 시간이 된다면 따로 학습을 해 보면 좋을 것이다.