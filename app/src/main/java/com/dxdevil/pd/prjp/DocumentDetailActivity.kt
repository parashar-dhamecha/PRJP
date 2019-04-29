package com.dxdevil.pd.prjp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.dxdevil.pd.prjp.Model.Request.Document.ListOfDocument
import com.dxdevil.pd.prjp.Model.Response.Document.ListOfDocument.ListOfDocumentResponse



import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DocumentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_document_detail)


        var token: String = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nGtV+sko7zYwM2NHV5S14ycsBfNho2BcEK/PG5HV/1akRCH5lZydkgmW1rA2HLckCkGT4ihzlL2hGeLdbkq9Q2r.V7HugwbHkLcyJrkNEt9svt-LlGaEAYqevDhHYxavuAEBearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.N9w0jV/v+9PKjqTQVRprezt4+qqzzgZZjY68hHbb/VdLYi/P2x192in+N/puRDAp8zEKMMqdnOPVvgPE6C6bukPznNX+g+jkb5sv8FfeS7Q7UXY/QIHjeYLQJ4Tk0Aziq/VdzyrDQLOq20vUdEYJjwIBiq1pk+R/7xBYPpEEPI6lQtTxggOw72uJnYt/kwviC0i6ppoGmWZHU5sX4ZyNBnhSp5RPMYvQKENl5w3MdkGet6cb9MaMD55LM0Ytqy2DMHMqW4H1t4ql13hFw3c/1MhX/sNci8G6WNp1Bo6qnFwi+eu6ABRNaQ0C4lXKjy4K/9Mj9ZN6dqQLCWdpsbW6Bx1K46PitJsHNo6bqFVlUWyxRHNeIJKu655Ema646p9o62EeO4LDIrUhOJLPMpXXDwbdcgHAsLBpM/TMfOdQlaLsB2d6rOlkqlh0n8ForetQ+M19xiDH0C+YxrDeDlnY6v62VfXDwwayEVEgmjZv2SM/CKPoyQgHsYEh3tGXECqP9UVxmqL+jPbLhIrTlku4Z1LsZXmRVc/gEq1/NJPIU7DtF1PEkfIs4VUHuwGClKyyQ2GfWmecbn5RZ4WF4HZpOr8KUDo5TsLVee+4w0W75MRPSaQZVRBz/AaW5owhvQ1DsBzHmO01XfKxj5oXASQUaSOA6OoAyFgrA+JHMi3g10sjOVFPhiHSDyOPenpYcw7rrxB7pXpVEdnW1gen3oT5BSJ+PzMfFkt2sg0Mg4KRFMMG0/cG6wP5C1W2gKQT2hVENRxfTYeifEjLE0oTVot9g+xlczLrrvAjqJS9SuHk+nGtV+sko7zYwM2NHV5S14ycsBfNho2BcEK/PG5HV/1akRCH5lZydkgmW1rA2HLckCkGT4ihzlL2hGeLdbkq9Q2r.V7HugwbHkLcyJrkNEt9svt-LlGaEAYqevDhHYxavuAE"

        var api = RetrofitClient.getInstance().api as Api
        var call = api.doclist(
            token, ListOfDocument(
                0,
                0,
                true,
                null,
                null,
                null,
                0,
                null,
                null,
                null
            )
        )as Call<ListOfDocumentResponse>


              try{
        call.enqueue(object : Callback<ListOfDocumentResponse> {
            override fun onFailure(call: Call<ListOfDocumentResponse>, t: Throwable) {
                Toast.makeText(this@DocumentActivity, "Check your connection", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<ListOfDocumentResponse>, response: Response<ListOfDocumentResponse>) {


                if (response.isSuccessful) {
                    Toast.makeText(this@DocumentActivity,"Response is Successfull", Toast.LENGTH_SHORT).show()
                  try {
                      var name = response.body()!!.data[0].documents[0].name.toString()
                      Toast.makeText(this@DocumentActivity, name,Toast.LENGTH_SHORT).show()
                  }catch (e:Exception)
                  {
                      Toast.makeText(this@DocumentActivity, e.message,Toast.LENGTH_LONG).show()
                  }
                    //Toast.makeText(this@DocumentActivity, name,Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this@DocumentActivity, "Response is Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }catch (e:Exception){
                  Toast.makeText(this@DocumentActivity,"Excetion",Toast.LENGTH_SHORT).show()
              }

    }
}
