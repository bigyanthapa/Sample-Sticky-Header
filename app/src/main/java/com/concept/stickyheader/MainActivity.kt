package com.concept.stickyheader

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.concept.stickyheader.data.Book
import com.concept.stickyheader.databinding.ActivityMainBinding
import com.concept.stickyheader.databinding.ViewListItemBinding
import com.concept.stickyheader.extensions.setDivider
import com.concept.stickyheader.utils.JsonUtils
import com.concept.stickyheader.utils.StickyHeaderDecoration

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val bookAdapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val books: List<Book> = JsonUtils.getItems(this)
        val groupedBooks: Map<Char, List<Book>> =
            books.groupBy { book -> book.title.first().toUpperCase() }
        bookAdapter.bookData = groupedBooks.toSortedMap()

        binding.bookList.setDivider(R.drawable.list_divider)
        binding.bookList.addItemDecoration(
            StickyHeaderDecoration(bookAdapter, binding.root)
        )
        binding.bookList.layoutManager = LinearLayoutManager(this)
        binding.bookList.adapter = bookAdapter
    }

    class BookAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        private var bookHeaders: List<Char> = listOf()

        var bookData: Map<Char, List<Book>> = emptyMap()
            set(value) {
                field = value
                bookHeaders = bookData.keys.toList()
                notifyDataSetChanged()
            }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
            val viewBinding: ViewListItemBinding =
                ViewListItemBinding.inflate(layoutInflater, parent, false)
            return BookViewHolder(viewBinding)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (position >= 0 && position < bookHeaders.size) {
                (holder as BookViewHolder).bind(bookHeaders[position])
            }
        }

        override fun getItemCount() = bookHeaders.size

        fun getHeaderForCurrentPosition(position: Int) = if (position in bookHeaders.indices) {
            bookHeaders[position]
        } else {
            ""
        }

        inner class BookViewHolder(private val viewBinding: ViewListItemBinding) :
            RecyclerView.ViewHolder(viewBinding.root) {

            fun bind(header: Char) {
                viewBinding.tvHeader.text = "$header"
                bookData[header]?.let { books ->
                    viewBinding.bookDetailsView.books = books
                }
            }
        }
    }
}