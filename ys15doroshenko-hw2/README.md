# Домашнее задание №2

	Реализовать класс предлагающий автодополнение по двум первым введенным буквам. 

	Класс PrefixMatches должен иметь следующий набор методов
```java
public class PrefixMatches {
	private Trie trie;
	/*
	 Формирует in-memory словарь слов.
	 Метод принимает слово, строку, массив слов/строк.
	 Если приходит строка, то она разбивается на слова по пробелам.
	 В словарь должны добавляться слова длиннее 2х символов. 
	 Предполагается что знаки пунктуации отсутствуют.
	*/
	public int add(String... strings) { ... }
	// есть ли слово в словаре
	public boolean contains(String word);
	// удаляет слово из словаря
	public boolean delete(String word);
	// к-во слов в словаре
	public int size();
	/*
	 если введенный pref длиннее или равен 2м символам,
	 то возвращает набор слов k разных длин начиная с минимальной, 
	 и начинающихся с данного префикса pref. 
	*/
	// Пример, даны слова следующей длины и pref='abc':
	// abc 3
	// abcd 4
	// abce 4
	// abcde 5
	// abcdef 6
	// - при k=1 возвращаются 'abc'
	// - при k=2 возвращаются 'abc', 'abcd', 'abce'
	// - при k=3 возвращаются 'abc', 'abcd', 'abce', 'abcde'
	// - при k=4 возвращаются 'abc', 'abcd', 'abce', 'abcde', 'abcdef'
	public Iterable<String> wordsWithPrefix(String pref, int k);
	/*
	 если введенный pref длиннее или равен 2м символам, 
	 то возвращает набор слов k=3 разных длин начиная с минимальной,
	 и начинающихся с данного префикса pref.
	*/
	public Iterable<String> wordsWithPrefix(String pref);
}
```

	Для представления in-memory словаря слов напишите класс RWayTrie реализующий интерфейс Trie. Как идею для реализации используйте R-way trie (или R^R-way trie) на 26 элементов.
```java
public interface Trie {
	// Добавляет в Trie пару слово - term, и его вес - weight.
	// В качестве веса используйте длину слова
	public void add(Tuple word);
	// есть ли слово в Trie
	public boolean contains(String word);
	// удаляет слово из Trie
	public boolean delete(String word);
	// итератор по всем словам, обход в ширину
	public Iterable<String> words();
	// итератор по всем словам, начинающимся с pref, обход в ширину
	public Iterable<String> wordsWithPrefix(String pref);
	// к-во слов в Trie
	public int size();
}
```
 - Для хранения промежуточных данных при обходе в ширину, используйте уже написанные динамический массив из предыдущего задания.
 - Для тестирования работоспособности используйте список слов - ftp://ftp.cs.princeton.edu/pub/cs226/autocomplete/words-333333.txt.