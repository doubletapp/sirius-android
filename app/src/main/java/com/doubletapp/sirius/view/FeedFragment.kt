package com.doubletapp.sirius.view


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.doubletapp.sirius.R
import com.doubletapp.sirius.extensions.showFragment
import com.doubletapp.sirius.model.FeedItem
import com.doubletapp.sirius.model.FeedItemType
import com.doubletapp.sirius.util.DecorationUtil
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = FeedAdapter(this)
        feedRecycler.addItemDecoration(DecorationUtil.SiriusItemDecoration.builder
                .setColor(android.R.color.transparent)
                .setContext(view.context)
                .setHeaderListener(adapter)
                .setOffset(R.dimen.feed_item_offset)
                .setSidesOffset(R.dimen.feed_item_sides_offset)
                .build())
        feedRecycler.adapter = adapter
        adapter.listener = object : FeedAdapter.OnFilterClickListener {
            override fun onFilterClick() {
                (activity as MainActivity).showFragment(android.R.id.content,
                        FeedFilterFragment(), FeedFilterFragment.TAG, true)
            }
        }
        val list = ArrayList<FeedItem>()
        list.add(FeedItem(0, FeedItemType.TYPE_STORIES))
        list.add(FeedItem(1, FeedItemType.TYPE_HEADER))
        list.add(FeedItem(2, FeedItemType.TYPE_FEED_CARD,
                "В «Сириусе» прошла лекция о том, что такое запах и на какие вопросы может ответить электронное обоняние. Спикер объясняет: запах, по сути, не что иное, как летучие молекулы, которые вместе с воздухом попадают в нашу носовую полость, растворяются и таким образом действуют на обонятельные рецепторы. Правда, они не всегда могут отличить натуральные запахи от искусственных, хорошие от плохих, распознать опасные химические вещества или летучие соединения, сопровождающие испорченные продукты питания. Решение этой проблемы ученые нашли в создании умных электронных устройств – мощных анализаторов запахов или так называемых «электронных носов», оснащенных сверхчувствительными сенсорами. К слову, у истоков уникальной разработки в России стояла сама Татьяна Кучменко.",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5be7420e6a0b6.jpg", 24, 15))
        list.add(FeedItem(3, FeedItemType.TYPE_FEED_CARD,
                "Как ошибка великого русского химика могла повлиять на британского нобелевского лауреата, рассказала профессор Института химии СПбГУ Анна Карцова",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5be426be3cf4d.jpg", 1, 5))
        list.add(FeedItem(4, FeedItemType.TYPE_FEED_CARD_2,
                "Открытый урок истории, приуроченный ко Дню народного единства, связал Сочи, несколько городов Тюменской и Пензенской области и Санкт-Петербург со столицей Чеченской Республики. Лекцию в формате видеомоста российским старшеклассникам провел преподаватель обществознания и права из Грозного, победитель Всероссийского конкурса «Учитель года России – 2018» Алихан Динаев. Организатором открытого урока выступила Президентская библиотека имени Б.Н. Ельцина.",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5be30ed24e382.jpg", 28, 3))
        list.add(FeedItem(5, FeedItemType.TYPE_FEED_CARD,
                "Сразу после окончания химического факультета Уральского государственного университета в 1977 году Сергей Москвин пришел работать педагогом в екатеринбургскую гимназии №9. Сегодня он обладатель многочисленных профессиональных наград, заслуженный учитель России, который побывал в 15 городах нашей страны с мастер-классами для учителей и лекциями для учеников. О том, что не так со школьной химией и почему химия нужна и физикам, и лирикам, мы поговорили с Сергеем Анатольевичем после одного из его занятий в «Сириусе».",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5be1afcb95163.jpg", 13, 0))
        list.add(FeedItem(6, FeedItemType.TYPE_FEED_CARD_2,
                "В «Сириусе» с лекцией выступил российский ученый, популяризатор науки Владимир Сурдин",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5bd3140c54d33.jpg", 145, 321))
        list.add(FeedItem(7, FeedItemType.TYPE_FEED_CARD,
                "Российский ученый, доктор биологических наук Евгений Рогаев рассказал на саммите «Большие вызовы для общества, государства и науки» об историческом контексте исследования болезни Альцгеймера",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5bc9e395c9757.jpg", 105, 63))
        list.add(FeedItem(8, FeedItemType.TYPE_FEED_CARD,
                "Какие особенности скелетов указывают на конкретное поведение и образ жизни, рассказал в «Сириусе» российский антрополог, кандидат биологических наук, доцент МГУ имени М.В. Ломоносова Станислав ",
                "https://sochisirius.ru/media/cache/post_thumb/uploads/post/5bd02610a0ff9.jpg", 14, 31))
        adapter.submitList(list)
    }
}
