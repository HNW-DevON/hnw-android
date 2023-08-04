package com.bestswlkh0310.sui.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bestswlkh0310.sui.R
import com.bestswlkh0310.sui.data.RetrofitClient
import com.bestswlkh0310.sui.data.res.CompResponse
import com.bestswlkh0310.sui.ui.com.CamState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class D(
    val d: String,
    val e: String,
    val i: Int
)

class HomeViewModel: ViewModel() {

    private val _state = MutableStateFlow(arrayListOf<D>(
        D("간병가사지원","주식회사 아름다운소행", R.drawable.ic_0),
        D("고용","주식회사 한국장애인인력지원", R.drawable.ic_1),
        D("관광운동","㈜ 도서출판점자", R.drawable.ic_2),
        D("교육","사회적협동조합 자바르떼 ", R.drawable.ic_3),
        D("문화예술","㈜ 추억을파는극장", R.drawable.ic_4),
        D("문화재","사단법인 문화진흥협회", R.drawable.ic_5),
        D("보건","수원의료복지 사회적협동조합", R.drawable.ic_6),
        D("보육","사단법인 함께하는 행복한 돌봄", R.drawable.ic_7),
        D("사회복지","주식회사 한마음희망나눔센터", R.drawable.ic_8),
        D("산림보전및관리","이풀약초협동조합", R.drawable.ic_9),
        D("청소","주식회사 코끼리공장", R.drawable.ic_10),
        D("환경","울산자원순환사업협동조합", R.drawable.ic_11),
        D("기타", "사회복지법인 손과손 핸인핸부평", R.drawable.ic_12)
    ))
    val state = _state.asStateFlow()
    private val _stat = MutableStateFlow<List<CompResponse>>(arrayListOf())
    val stat = _stat.asStateFlow()

    //    val clicked =
    fun get(s: String) {
        viewModelScope.launch {
            _stat.value = RetrofitClient.retrofit.s(s)
        }
    }
}