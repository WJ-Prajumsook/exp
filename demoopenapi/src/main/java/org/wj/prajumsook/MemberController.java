package org.wj.prajumsook;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.wj.prajumsook.entity.Member;
import org.wj.prajumsook.model.ErrorObject;
import org.wj.prajumsook.repository.MemberRepository;

import java.util.Optional;

@RestController
@RequestMapping("/member")
@AllArgsConstructor
@Tag(name = "member", description = "The member API")
public class MemberController {

    private MemberRepository memberRepository;

    @Operation(
            summary = "Find all members",
            description = "Find all members",
            tags = "member"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = Member.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @GetMapping(produces = "application/json")
    public ResponseEntity<?> all() {
        return ResponseEntity.ok(memberRepository.findAll());
    }

    @Operation(
            summary = "Find member by ID",
            description = "Search member by the id",
            tags = "member"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = Member.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Member not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> byId(@PathVariable(value = "id")Integer id) {
        try {
            Optional<Member> opt = memberRepository.findById(id);
            if(opt.isPresent()) {
                return ResponseEntity.ok(opt.get());
            } else {
                return notFound();
            }
        } catch (Exception exp) {
            return badRequest(exp);
        }
    }

    @Operation(
            summary = "Create new member",
            description = "Create a new member",
            tags = "member"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = Member.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Member already exists",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> save(@RequestBody Member member) {
        try {
            Optional<Member> optionalMember = memberRepository.findById(member.getId());
            if(optionalMember.isPresent()) {
                return conflict();
            } else {
                return ResponseEntity.ok(memberRepository.save(member));
            }
        } catch (Exception exp) {
            return badRequest(exp);
        }
    }

    @Operation(
            summary = "Update member",
            description = "Update member info",
            tags = "member"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = Member.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Member not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @PutMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> update(@RequestBody Member member) {
        try {
            Optional<Member> optionalMember = memberRepository.findById(member.getId());
            if(optionalMember.isPresent()) {
                return ResponseEntity.ok(memberRepository.save(member));
            } else {
                return notFound();
            }
        } catch (Exception exp) {
            return badRequest(exp);
        }
    }

    @Operation(
            summary = "Delete a member",
            description = "Delete a member",
            tags = "member"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(
                                    schema = @Schema(implementation = Member.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Member not found",
                            content = @Content(
                                    schema = @Schema(implementation = ErrorObject.class)
                            )
                    )
            }
    )
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Integer id) {
        try {
            Optional<Member> optionalMember = memberRepository.findById(id);
            if(optionalMember.isPresent()) {
                Member member = optionalMember.get();
                memberRepository.delete(member);
                return ResponseEntity.ok(member);
            } else {
                return notFound();
            }
        } catch (Exception exp) {
            return badRequest(exp);
        }
    }

    private ResponseEntity<?> notFound() {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.NOT_FOUND.toString(), "Member not found"),
                HttpStatus.NOT_FOUND
        );
    }

    private ResponseEntity<?> badRequest(Throwable throwable) {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.BAD_REQUEST.toString(), "Bad request"),
                HttpStatus.BAD_REQUEST
        );
    }

    private ResponseEntity<?> conflict() {
        return new ResponseEntity<>(
                new ErrorObject(HttpStatus.CONFLICT.toString(), "Member already exists"),
                HttpStatus.CONFLICT
        );
    }
}
