// IBookManager.aidl
package com.ecar.epark.binderdemo.aidl;

// Declare any non-default types here with import statements
import com.ecar.epark.binderdemo.aidl.Book;

interface IBookManager {
    List<Book> getBookList();
    void addBook(in Book book);
}
