
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.konsercb.database.Person
import com.example.konsercb.repositori.RepositoriPerson
import java.sql.Timestamp

class EntryPersonViewModel(private val repositoriPerson: RepositoriPerson): ViewModel(){
    /*
    *Berisi status Person saat ini
     */
    var uiStatePerson by mutableStateOf(UIStatePerson())
        private set

    /* Fungsi untuk memvalidasi input */
    private fun validasiInput(uiState: DetailPerson = uiStatePerson.detailPerson): Boolean {
        return with(uiState){
            namaperson.isNotBlank() && emailperson.isNotBlank() && identitas.isNotBlank() && nohp.isNotBlank()
        }
    }

    fun updateUiState(detailPerson: DetailPerson){
        uiStatePerson = UIStatePerson(detailPerson = detailPerson, isEntryValid = validasiInput(detailPerson))
    }

    /* Fungsi untuk menyimpan data yang di-entry */
    suspend fun savePerson(){
        if (validasiInput()) {
            repositoriPerson.insertPerson(uiStatePerson.detailPerson.toPerson())
        }
    }
}

data class UIStatePerson(
    val detailPerson: DetailPerson = DetailPerson(),
    val isEntryValid: Boolean = false
)

data class DetailPerson(
    val id: Int = 0,
    val namaperson: String = "",
    val emailperson: String = "",
    val identitas: String = "",
    val nohp: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

/* Fungsi untuk mengkonversi data input ke data dalam tabel sesuai jenis datanya */
fun DetailPerson.toPerson(): Person = Person(
    id = id,
    namaperson = namaperson,
    emailperson = emailperson,
    identitas = identitas,
    nohp = nohp,
)

fun Person.toUiStatePerson(isEntryValid: Boolean = false): UIStatePerson = UIStatePerson(
    detailPerson = this.toDetailPerson(),
    isEntryValid = isEntryValid
)

fun Person.toDetailPerson(): DetailPerson = DetailPerson(
    id = id,
    namaperson = namaperson,
    emailperson = emailperson,
    identitas = identitas,
    nohp = nohp,
)