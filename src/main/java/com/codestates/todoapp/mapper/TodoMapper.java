package com.codestates.todoapp.mapper;

import com.codestates.todoapp.dto.PatchDto;
import com.codestates.todoapp.dto.PostDto;
import com.codestates.todoapp.dto.ResponseDto;
import com.codestates.todoapp.entity.Todos;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    Todos postDtoToTodo(PostDto postDto);
    Todos patchDtoToTodo(PatchDto patchDto);
    ResponseDto todoToResponseDto(Todos todos);
    List<ResponseDto> todosToResponseDtos(List<Todos> todos);
}
